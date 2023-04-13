package org.kryptokrona.api.routes

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals

class OutputRoutesTest {

    @Test
    fun `can get all outputs`() = testApplication {
        client.get("/api/v1/nodes").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

}