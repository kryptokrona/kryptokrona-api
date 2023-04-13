package org.kryptokrona.api.routes

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals

class TransactionRoutesTest {

    @Test
    fun `can get all transactions`() = testApplication {
        client.get("/api/v1/transactions").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

}