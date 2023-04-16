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
import org.kryptokrona.api.config.InitialSyncConfig
import org.kryptokrona.api.models.Node
import org.kryptokrona.api.models.response.NodeListResponse
import org.slf4j.LoggerFactory

class InitialSyncer {

    private val logger = LoggerFactory.getLogger("InitialSyncer")

    suspend fun sync(): Unit = coroutineScope {
        launch {
            logger.info("Starting Initial Syncer...")

            while (isActive) {
                syncNodeList()

                delay(InitialSyncConfig.SYNC_INTERVAL)
            }
        }
    }

    suspend fun syncNodeList(): Unit = coroutineScope {
        launch(Dispatchers.IO) {
            while (isActive) {
                logger.info("Fetching new node list...")

                val nodeListResponse =
                    client.get("https://raw.githubusercontent.com/kryptokrona/kryptokrona-nodes-list/master/nodes.json")
                        .body<NodeListResponse>()

                nodeListResponse.nodes.forEach { node ->
                    saveNode(node)
                }

                delay(InitialSyncConfig.SYNC_INTERVAL_NODE_LIST)
            }
        }
    }

    suspend fun saveNode(node: Node): Unit = coroutineScope {
        launch(Dispatchers.IO) {
            logger.info("Saving node...")

            // save to db if not exists

            // if it exists, we update some fields in the database (height, etc)
        }
    }

}