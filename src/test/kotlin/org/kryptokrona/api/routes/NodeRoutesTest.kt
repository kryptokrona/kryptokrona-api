package org.kryptokrona.api.routes

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals

class NodeRoutesTest {

    @Test
    fun `can get all nodes`() = testApplication {
        client.get("/api/v1/nodes").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

}