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

package org.kryptokrona.api.routes

import io.bkbn.kompendium.core.metadata.PostInfo
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kryptokrona.api.models.request.NodeRequest
import org.kryptokrona.api.models.response.ExceptionResponse
import org.kryptokrona.api.utils.Metrics
import org.kryptokrona.api.utils.jsonObjectMapper
import org.kryptokrona.sdk.http.client.NodeClient
import org.kryptokrona.sdk.http.model.node.Info
import org.kryptokrona.sdk.util.node.Node

fun Route.infoRoute() {
    route("/v1/info") {
        route("/node") {
            nodeDocumentation()

            post {
                val nodeRequest = call.receive<NodeRequest>()

                val node = Node(nodeRequest.hostName, nodeRequest.port, nodeRequest.ssl)
                val nodeClient = NodeClient(node)
                val result = nodeClient.getNodeInfo()

                val json = jsonObjectMapper().writeValueAsString(result)

                call.respond(HttpStatusCode.OK, json)
            }
        }

        // should not use docs since it will only get exposed to prometheus
        route("/requests/metrics") {
            get {
                call.respond(Metrics.getRegistry().scrape())
            }
        }
    }
}

private fun Route.nodeDocumentation() {
    install(NotarizedRoute()) {
        tags = setOf("info")
        post = PostInfo.builder {
            summary("Get node info")
            description("Gets a specific node information.")
            response {
                responseCode(HttpStatusCode.OK)
                responseType<Info>()
                description("Will return node information.")
            }
            canRespond {
                responseType<ExceptionResponse>()
                responseCode(HttpStatusCode.BadRequest)
                description("Could not handle the request.")
            }
            canRespond {
                responseType<ExceptionResponse>()
                responseCode(HttpStatusCode.InternalServerError)
                description("Some serious trouble is going on.")
            }
        }
    }
}