// Copyright (c) 2023-2023, The Kryptokrona Developers
//
// Written by Marcus Cvjeticanin
//
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without modification, are
// permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice, this list of
//    conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright notice, this list
//    of conditions and the following disclaimer in the documentation and/or other
//    materials provided with the distribution.
//
// 3. Neither the name of the copyright holder nor the names of its contributors may be
//    used to endorse or promote products derived from this software without specific
//    prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
// EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
// THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
// PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
// STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
// THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package org.kryptokrona.api.syncers


import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.kryptokrona.api.config.HuginConfig
import org.kryptokrona.api.models.PostEncrypted
import org.kryptokrona.api.models.PostEncryptedGroup
import org.kryptokrona.api.services.PostEncryptedGroupServiceImpl
import org.kryptokrona.api.services.PostEncryptedServiceImpl
import org.kryptokrona.api.utils.Box
import org.kryptokrona.api.utils.SealedBox
import org.kryptokrona.api.utils.trimExtra
import org.kryptokrona.sdk.http.client.PoolChangesClient
import org.kryptokrona.sdk.http.model.transaction.Transaction
import org.kryptokrona.sdk.util.node.Node
import org.slf4j.LoggerFactory
import java.lang.System.getenv


class HuginSyncer {

    private val logger = LoggerFactory.getLogger("HuginSyncer")

    private val postEncryptedServiceImpl: PostEncryptedServiceImpl = PostEncryptedServiceImpl()

    private val postEncryptedGroupServiceImpl: PostEncryptedGroupServiceImpl = PostEncryptedGroupServiceImpl()

    private val node: Node = Node(
        getenv("NODE_HOSTNAME").toString(),
        getenv("NODE_PORT").toInt(),
        getenv("NODE_SSL").toBoolean()
    )

    private val poolChangesClient: PoolChangesClient = PoolChangesClient(node)

    private var knownPoolTxsList: List<String> = listOf()

    suspend fun sync() = coroutineScope {
        launch {
            while(isActive) {
                logger.info("Fetching encrypted posts...")

                // get the data from the pool
                val retrievedData = poolChangesClient.getPoolChangesLite()
                val transactions = retrievedData?.addedTxs

                // if transactions is not null
                transactions?.let {
                    val extra = it[0].transactionPrefix.extra
                    val transactionHash = it[0].transactionHash
                    logger.info("Incoming transaction $transactionHash")

                    // add the transaction to the known list if it is not already known
                    addTxIfNotKnown(transactionHash)

                    // validate that the extra data is longer than 200 characters
                    if (extra.length > 200) {
                        logger.info("Trimming extra data...")
                        val extraData = trimExtra(extra)
                        if ("box" in extraData) savePostEncrypted(extraData, it[0])
                        if ("sb" in extraData) savePostEncryptedGroup(extraData, it[0])
                    } else {
                        logger.debug("Extra is less than 200 in length, skipping...")
                    }
                }

                delay(HuginConfig.SYNC_INTERVAL)
            }
        }
    }

    private fun addTxIfNotKnown(txHash: String) {
        knownPoolTxsList.contains(txHash).let { isKnown ->
            if (!isKnown) {
                knownPoolTxsList += txHash
            } else {
                logger.info("Transaction is known, skipping...")
            }
        }
    }

    private suspend fun savePostEncrypted(extraData: String, transaction: Transaction) = coroutineScope {
        val boxObj: Box = Json.decodeFromString(extraData)
        logger.info("Parsed box: ${boxObj.timestamp}")

        launch {
            val exists = postEncryptedServiceImpl.existsByTxBox(boxObj.box)

            if (!exists) {
                logger.info("Saving encrypted post...")

                //TODO: this and the other savePostEncryptedGroup should perhaps include more fields???
                // now it blocks the thread here for some reason. existsByTxBox() is not blocking anymore.
                val postEncrypted = PostEncrypted().apply {
                    txHash = transaction.transactionHash
                    txBox = boxObj.box
                    txTimestamp = boxObj.timestamp
                }
                postEncryptedServiceImpl.save(postEncrypted)
            }
        }
    }

    private suspend fun savePostEncryptedGroup(extraData: String, transaction: Transaction) = coroutineScope {
        val sealedBoxObj: SealedBox = Json.decodeFromString(extraData)
        logger.info("Parsed SealedBox: ${sealedBoxObj.timestamp}")

        launch {
            val exists = postEncryptedGroupServiceImpl.existsByTxSb(sealedBoxObj.secretBox)

            if (!exists) {
                logger.info("Saving encrypted group post...")
                val postEncryptedGroup = PostEncryptedGroup().apply {
                    txHash = transaction.transactionHash
                    txSb = sealedBoxObj.secretBox
                    txTimestamp = sealedBoxObj.timestamp
                }
                postEncryptedGroupServiceImpl.save(postEncryptedGroup)
            }
        }
    }

}
