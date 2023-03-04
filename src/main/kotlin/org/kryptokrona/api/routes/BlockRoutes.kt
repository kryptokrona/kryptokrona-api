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

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kryptokrona.api.services.BlockServiceImpl
import org.ktorm.jackson.KtormModule

val service = BlockServiceImpl()

fun Route.blocksRoute() {
    get("/v1/blocks") {
        val blocks = service.getAll()

        val mapper: ObjectMapper = JsonMapper.builder()
            .addModule(JavaTimeModule())
            .addModule(KtormModule())
            .build()

        val json = mapper.writeValueAsString(blocks)
        call.respond(HttpStatusCode.OK, json)
    }
}

fun Route.blocksByIdRoute() {
    get("/v1/blocks/{id}") {
        val id = call.parameters["id"]?.toIntOrNull()

        id?.let {
            val block = service.getById(id)


             block?.let {
                 val mapper: ObjectMapper = JsonMapper.builder()
                     .addModule(JavaTimeModule())
                     .addModule(KtormModule())
                     .build()

                 // mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                 val json = mapper.writeValueAsString(block)

                 call.respond(HttpStatusCode.Found, json)
             } ?: call.respond(HttpStatusCode.NotFound, "No block found with id $id")
        } ?: call.respond(HttpStatusCode.BadRequest)
    }
}
