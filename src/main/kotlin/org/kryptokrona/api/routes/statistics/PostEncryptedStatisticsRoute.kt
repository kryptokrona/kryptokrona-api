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

package org.kryptokrona.api.routes.statistics

import io.bkbn.kompendium.core.metadata.GetInfo
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kryptokrona.api.models.response.ExceptionResponse
import org.kryptokrona.api.models.response.ResultResponse
import org.kryptokrona.api.services.postencrypted.PostEncryptedStatisticsServiceImpl
import org.kryptokrona.api.utils.jsonObjectMapper

private val service = PostEncryptedStatisticsServiceImpl()

fun Route.postsEncryptedStatisticsRoute() {
    route("/v1/statistics/post-encrypted") {
        route("/1h") {
            allPostEncryptedStatistic1hDocumentation()

            get {
                val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
                val size = call.request.queryParameters["size"]?.toIntOrNull() ?: 10
                val items = service.get1h(size, page)
                val totalCount = service.getTotal1h()

                val result = ResultResponse(items, page, size, totalCount)
                val json = jsonObjectMapper().writeValueAsString(result)

                call.respond(HttpStatusCode.OK, json)
            }
        }

        route("/24h") {
            allPostEncryptedStatistic24hDocumentation()

            get {
                val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
                val size = call.request.queryParameters["size"]?.toIntOrNull() ?: 10
                val items = service.get24h(size, page)
                val totalCount = service.getTotal24h()

                val result = ResultResponse(items, page, size, totalCount)
                val json = jsonObjectMapper().writeValueAsString(result)

                call.respond(HttpStatusCode.OK, json)
            }
        }

        route("/1w") {
            allPostEncryptedStatistic1wDocumentation()

            get {
                val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
                val size = call.request.queryParameters["size"]?.toIntOrNull() ?: 10
                val items = service.get1w(size, page)
                val totalCount = service.getTotal1w()

                val result = ResultResponse(items, page, size, totalCount)
                val json = jsonObjectMapper().writeValueAsString(result)

                call.respond(HttpStatusCode.OK, json)
            }
        }

        route("/1m") {
            allPostEncryptedStatistic1mDocumentation()

            get {
                val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
                val size = call.request.queryParameters["size"]?.toIntOrNull() ?: 10
                val items = service.get1m(size, page)
                val totalCount = service.getTotal1m()

                val result = ResultResponse(items, page, size, totalCount)
                val json = jsonObjectMapper().writeValueAsString(result)

                call.respond(HttpStatusCode.OK, json)
            }
        }

        route("/1y") {
            allPostEncryptedStatistic1yDocumentation()

            get {
                val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
                val size = call.request.queryParameters["size"]?.toIntOrNull() ?: 10
                val items = service.get1y(size, page)
                val totalCount = service.getTotal1y()

                val result = ResultResponse(items, page, size, totalCount)
                val json = jsonObjectMapper().writeValueAsString(result)

                call.respond(HttpStatusCode.OK, json)
            }
        }
    }
}

private fun Route.allPostEncryptedStatistic1hDocumentation() {
  install(NotarizedRoute()) {
    get = GetInfo.builder {
      summary("Get all encrypted posts for the last hour")
      description("Gets all encrypted posts stored in the database for the last hour.")
      response {
        responseCode(HttpStatusCode.OK)
        responseType<ResultResponse>()
        description("Will return all encrypted posts for the last hour.")
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

private fun Route.allPostEncryptedStatistic24hDocumentation() {
  install(NotarizedRoute()) {
    get = GetInfo.builder {
      summary("Get all encrypted posts for the last 24 hours")
      description("Gets all encrypted posts stored in the database for the last 24 hours.")
      response {
        responseCode(HttpStatusCode.OK)
        responseType<ResultResponse>()
        description("Will return all encrypted posts for the last 24 hours.")
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

private fun Route.allPostEncryptedStatistic1wDocumentation() {
  install(NotarizedRoute()) {
    get = GetInfo.builder {
      summary("Get all encrypted posts for the last week")
      description("Gets all encrypted posts stored in the database for the last week.")
      response {
        responseCode(HttpStatusCode.OK)
        responseType<ResultResponse>()
        description("Will return all encrypted posts for the last week.")
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

private fun Route.allPostEncryptedStatistic1mDocumentation() {
  install(NotarizedRoute()) {
    get = GetInfo.builder {
      summary("Get all encrypted posts for the last month")
      description("Gets all encrypted posts stored in the database for the last month.")
      response {
        responseCode(HttpStatusCode.OK)
        responseType<ResultResponse>()
        description("Will return all encrypted posts for the last month.")
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

private fun Route.allPostEncryptedStatistic1yDocumentation() {
  install(NotarizedRoute()) {
    get = GetInfo.builder {
      summary("Get all encrypted posts for the last year")
      description("Gets all encrypted posts stored in the database for the last year.")
      response {
        responseCode(HttpStatusCode.OK)
        responseType<ResultResponse>()
        description("Will return all encrypted posts for the last year.")
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