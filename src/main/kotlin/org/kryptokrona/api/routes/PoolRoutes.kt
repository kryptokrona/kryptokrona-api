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

import io.bkbn.kompendium.core.metadata.GetInfo
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.bkbn.kompendium.json.schema.definition.TypeDefinition
import io.bkbn.kompendium.oas.payload.Parameter
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kryptokrona.api.models.Hashrate
import org.kryptokrona.api.models.Pool
import org.kryptokrona.api.models.response.ExceptionResponse
import org.kryptokrona.api.models.response.ResultResponse
import org.kryptokrona.api.services.pool.PoolServiceImpl
import org.kryptokrona.api.utils.jsonObjectMapper

private val service = PoolServiceImpl()

fun Route.poolsRoute() {
    route("/v1/pools") {
        route("") {
            allPoolDocumentation()

            get {
                val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
                val size = call.request.queryParameters["size"]?.toIntOrNull() ?: 10

                val items = service.getAll(size, page)
                val totalCount = service.getTotalCount()

                val result = ResultResponse(items, page, size, totalCount)
                val json = jsonObjectMapper().writeValueAsString(result)

                call.respond(HttpStatusCode.OK, json)
            }
        }

        route("/{id}") {
            getPoolByIdDocumentation()

            get {
                val id = call.parameters["id"]?.toLongOrNull()

                id?.let {
                    val item = service.getById(id)

                    item?.let {
                        val json = jsonObjectMapper().writeValueAsString(item)

                        call.respond(HttpStatusCode.Found, json)
                    } ?: call.respond(HttpStatusCode.NotFound, "No pool found with id $id")
                } ?: call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}

private fun Route.allPoolDocumentation() {
    install(NotarizedRoute()) {
        get = GetInfo.builder {
            summary("Get all pools")
            description("Gets all pools stored in the database.")
            response {
                responseCode(HttpStatusCode.OK)
                responseType<ResultResponse>()
                description("Will return all pools.")
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

private fun Route.getPoolByIdDocumentation() {
    install(NotarizedRoute()) {
        parameters = listOf(
            Parameter(
                name = "id",
                `in` = Parameter.Location.path,
                schema = TypeDefinition.STRING
            )
        )
        get = GetInfo.builder {
            summary("Get a specific pool by ID")
            description("Get a specific pool by ID stored in the database.")
            response {
                responseCode(HttpStatusCode.OK)
                responseType<Pool>()
                description("Will return a pool.")
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