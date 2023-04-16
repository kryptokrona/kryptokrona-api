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

import com.jessecorbett.diskord.api.channel.ChannelClient
import com.jessecorbett.diskord.bot.bot
import com.jessecorbett.diskord.bot.classicCommands
import com.jessecorbett.diskord.internal.client.RestClient
import com.jessecorbett.diskord.util.sendMessage
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.kryptokrona.api.config.DiscordConfig.CHANNEL_ID
import org.kryptokrona.api.config.DiscordConfig.DELAY_MS
import org.kryptokrona.api.config.DiscordConfig.TOKEN
import org.kryptokrona.sdk.http.client.NodeClient
import org.kryptokrona.sdk.util.node.Node
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.time.LocalDateTime.now

data class NodeStatus(
    val node: Node,
    var isRunning: Boolean,
    var height: Int?,
    var upToDate: Boolean,
    var lastResponse: LocalDateTime,
)

class DiscordSyncer {

    private val logger = LoggerFactory.getLogger("DiscordSyncer")

    // TODO remove hard coded values here and fetch from database once it's implemented
    private val nodes = listOf(
        Node("blocksum.org", 11898, false),
        Node("privacymine.net", 11898, false),
        Node("privacymine.net", 21898, true),
        Node("techy.ddns.net", 11898, false),
        Node("wasa.kryptokrona.se", 11898, false),
        Node("tifo.info", 11898, false),
    )

    private val nodeStatuses = mutableListOf<NodeStatus>()

    init {
        // initialize node statuses
        nodes.forEach {
            nodeStatuses.add(NodeStatus(it, true, 0, true, now()))
        }
    }

    suspend fun sync(): Unit = coroutineScope {
        launch {
            logger.info("Starting Discord Syncer...")

            while(isActive) {
                nodes.forEach { node ->
                    logger.info("Checking node ${node.hostName}...")
                    val cl = NodeClient(node)
                    val ns = nodeStatuses.find { nodeStatus -> nodeStatus.node.hostName == node.hostName }
                    val currentNode = nodeStatuses.find { it.node == node }?.node
                    val currentNodeStatus = nodeStatuses.find { it.node == currentNode } ?: return@forEach // exit early if node not found

                    // if the node is down, send a message to discord
                    // we also check if the last state was up to avoid spamming the channel
                    if (!cl.isNodeRunning() && currentNodeStatus.isRunning) {
                        sendDiscordMessage(
                            """
                                |❌ ALERT: ${node.hostName} is **DOWN**!
                                |--------------------------------
                                |Last response: ${ns?.lastResponse}
                            """.trimMargin()
                        )

                        // set isRunning to false
                        currentNodeStatus.isRunning = false
                    }

                    // if the node is up, send a message to discord
                    // we also check if the last state was down to avoid spamming the channel
                    if (cl.isNodeRunning() && !currentNodeStatus.isRunning) {
                        // set current node height
                        currentNodeStatus.height = cl.getNodeHeight()?.height

                        sendDiscordMessage(
                            """
                                |✅ ${node.hostName} is **UP** again!
                                |📊 Height: **${cl.getNodeHeight()?.height}**
                                |--------------------------------
                                |*Pushing it to the limit!* ⚡
                            """.trimMargin()
                        )

                        // set date and time of last response here
                        currentNodeStatus.lastResponse = now()
                    }

                    val highestHeight = nodeStatuses.maxOfOrNull { it.height ?: 0 } ?: 0
                    val heightDiff = (highestHeight - (currentNodeStatus.height ?: 0))

                    // if the node is not up-to-date, send a message to discord
                    // we also check if the last state was up-to-date to avoid spamming the channel
                    if (heightDiff >= 5 && currentNodeStatus.upToDate) {
                        sendDiscordMessage(
                            """
                                |⚠️ IS NOT UP TO DATE: ${node.hostName}
                                |📊 Height: **${cl.getNodeHeight()?.height}**
                                |--------------------------------
                                |*The network is counting on you!* ⚡
                            """.trimMargin()
                        )

                        currentNodeStatus.upToDate = false
                    }
                }

                // if all nodestatuses is running and up-to-date, send a message to discord
                val allNodesRunning = nodeStatuses.all { it.isRunning }

                if (allNodesRunning) {
                    sendDiscordMessage(
                        """
                            |❇️ PUBLIC NODES FULLY OPERATIONAL!
                            |--------------------------------
                            |*All nodes online! We've got ourselves a Node Party in here!* 🥳
                        """.trimMargin()
                    )
                }

                delay(DELAY_MS)
            }
        }
    }

    private suspend fun sendDiscordMessage(message: String): Unit = coroutineScope {
        // launch a separate coroutine to send messages
        val job = launch {
            val client = RestClient.default(TOKEN)
            val channel = ChannelClient(CHANNEL_ID, client)
            channel.sendMessage(message)
        }

        bot(TOKEN) {
            classicCommands {
                command("shutdown") { message ->
                    message.reply("Stopping Discord syncer...")
                    shutdown()

                    // stop the coroutine
                    job.cancel()
                }
            }
        }
    }

}