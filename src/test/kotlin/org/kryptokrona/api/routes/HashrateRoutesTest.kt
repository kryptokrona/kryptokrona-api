package org.kryptokrona.api.routes

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals

class HashrateRoutesTest {

    @Test
    fun `can get all hashrates`() = testApplication {
        client.get("/api/v1/hashrates").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

}