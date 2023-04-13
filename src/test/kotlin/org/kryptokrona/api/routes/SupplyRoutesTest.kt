package org.kryptokrona.api.routes

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals

class SupplyRoutesTest {

    @Test
    fun `can get all supplies`() = testApplication {
        client.get("/api/v1/supplies").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

}