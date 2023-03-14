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
import org.kryptokrona.api.config.HuginConfig
import org.kryptokrona.api.services.PostEncryptedGroupServiceImpl
import org.kryptokrona.api.services.PostEncryptedServiceImpl
import org.kryptokrona.sdk.core.node.Node
import org.kryptokrona.sdk.http.client.PoolChangesClient
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

    private val poolChangesLiteClient: PoolChangesClient = PoolChangesClient(node)

    private var knownPoolTxsList: List<String> = mutableListOf()

    suspend fun postEncryptedSync() = coroutineScope {
        launch(Dispatchers.IO) {
            while(isActive) {
                logger.info("Fetching encrypted posts...")

                val data = poolChangesLiteClient.getPoolChangesLite()
                val transactions = data?.addedTxs

                transactions?.let {
                    logger.info("Fetched ${transactions.size} transactions.")

                    val extra = it[0].transactionPrefix.extra
                    val transactionHash = it[0].transactionHash

                    knownPoolTxsList.contains(transactionHash).let { isKnown ->
                        if (!isKnown) {
                            logger.info("Transaction is not known, saving...")
                            knownPoolTxsList += transactionHash
                            savePostEncrypted()
                        } else {
                            logger.info("Transaction is known, skipping...")
                        }
                    }

                    if (extra.length > 200) {
                        logger.info("Extra is more than 200 in length, saving...")
                    } else {
                        logger.info("Extra is less than 200 in length, skipping...")
                    }

                } ?: logger.info("Fetched 0 transactions.")

                delay(HuginConfig.POST_ENCRYPTED_SYNC_INTERVAL)
            }
        }
    }

    suspend fun postEncryptedGroupSync() = coroutineScope {
        launch(Dispatchers.IO) {
            while(isActive) {
                logger.info("Fetching encrypted group posts...")
                // walletSyncData.let { logger.info("Fetched ${it?.items?.size} blocks") }
                delay(HuginConfig.POST_ENCRYPTED_GROUP_SYNC_INTERVAL)
            }
        }
    }

    private suspend fun savePostEncrypted() = coroutineScope {
        logger.info("Saving encrypted post...")
    }

    private suspend fun savePostEncryptedGroup() = coroutineScope {
        logger.info("Saving encrypted group post...")
    }

}
