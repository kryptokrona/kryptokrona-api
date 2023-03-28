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
import org.kryptokrona.api.config.DiscordConfig.MESSAGE_COUNT
import org.kryptokrona.api.config.DiscordConfig.TOKEN
import org.slf4j.LoggerFactory

class DiscordSyncer {

    private val logger = LoggerFactory.getLogger("DiscordSyncer")

    suspend fun sync(): Unit = coroutineScope {
        launch {
            while(isActive) {
                logger.info("Starting Discord syncer...")
                delay(DELAY_MS)
            }
        }
    }

    suspend fun sendDiscordMessage(message: String): Unit = coroutineScope {
        // launch a separate coroutine to send messages
        val job = launch {
            val client = RestClient.default(TOKEN)
            val channel = ChannelClient(CHANNEL_ID, client)
            for (count in 0..MESSAGE_COUNT) {
                channel.sendMessage("This is message ${count + 1} out of $MESSAGE_COUNT.")

                delay(DELAY_MS)
            }
        }

        bot(TOKEN) {
            classicCommands {
                command("shutdown") { message ->
                    message.reply("Stopping bot.")
                    shutdown()

                    // stop the coroutine
                    job.cancel()
                }
            }
        }
    }

}