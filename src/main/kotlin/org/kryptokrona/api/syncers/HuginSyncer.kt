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
import org.kryptokrona.api.models.PostsEncrypted
import org.kryptokrona.api.services.PostEncryptedGroupServiceImpl
import org.kryptokrona.api.services.PostEncryptedServiceImpl
import org.kryptokrona.api.utils.Box
import org.kryptokrona.api.utils.NewBox
import org.kryptokrona.api.utils.SealedBox
import org.kryptokrona.api.utils.trimExtra
import org.kryptokrona.sdk.http.client.PoolChangesClient
import org.kryptokrona.sdk.http.model.transaction.Transaction
import org.kryptokrona.sdk.util.node.Node
import org.slf4j.LoggerFactory
import java.lang.System.getenv
import java.time.LocalDateTime.now


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
                    for (tx in it) {
                        logger.info("Incoming transaction ${tx.transactionHash}")

                        val extra = tx.transactionPrefix.extra
                        val transactionHash = tx.transactionHash

                        // validate that the extra data is longer than 200 characters and that the transaction is not in the known pool txs list
                        if (extra.length > 200 && transactionHash !in knownPoolTxsList) {
                            knownPoolTxsList += transactionHash
                            val extraData = trimExtra(extra)

                            if ("box" in extraData) savePostEncrypted(extraData, tx)
                            else if ("sb" in extraData) savePostEncryptedGroup(extraData, tx)
                        } else {
                            logger.debug("Extra is less than 200 in length, skipping...")
                        }
                    }
                }

                delay(HuginConfig.SYNC_INTERVAL)
            }
        }
    }

    private suspend fun savePostEncrypted(extraData: String, transaction: Transaction): Unit = coroutineScope {
        launch {
            // check if the new viewKey and txKey is in the extra data
            if ("vt" in extraData && "txKey" in extraData) {
                val newBoxObj: NewBox = Json.decodeFromString(extraData)
                val postEncrypted = PostEncrypted {
                    txHash = transaction.transactionHash
                    txBox = newBoxObj.box
                    txTimestamp = newBoxObj.timestamp
                    createdAt = now()
                }
                postEncryptedServiceImpl.save(postEncrypted)
            } else {
                val boxObj: Box = Json.decodeFromString(extraData)
                val postEncrypted = PostEncrypted {
                    txHash = transaction.transactionHash
                    txBox = boxObj.box
                    txTimestamp = boxObj.timestamp
                    createdAt = now()
                }
                postEncryptedServiceImpl.save(postEncrypted)
            }
        }
    }

    private suspend fun savePostEncryptedGroup(extraData: String, transaction: Transaction): Unit = coroutineScope {
        launch {
            val sealedBoxObj: SealedBox = Json.decodeFromString(extraData)
            postEncryptedGroupServiceImpl.existsByTxSb(sealedBoxObj.secretBox).let {
                val postEncryptedGroup = PostEncryptedGroup {
                    txHash = transaction.transactionHash
                    txSb = sealedBoxObj.secretBox
                    txTimestamp = sealedBoxObj.timestamp
                    createdAt = now()
                }
                postEncryptedGroupServiceImpl.save(postEncryptedGroup)
            }
        }
    }

}
