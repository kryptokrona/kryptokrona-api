package org.kryptokrona.api.routes

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals

class PostEncryptedGroupRoutesTest {

    @Test
    fun `can get all encrypted group posts`() = testApplication {
        client.get("/api/v1/posts-encrypted-group").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

}