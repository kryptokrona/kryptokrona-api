package org.kryptokrona.api.routes

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals

class PostEncryptedRoutesTest {

    @Test
    fun `can get all encrypted posts`() = testApplication {
        client.get("/api/v1/posts-encrypted").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

}