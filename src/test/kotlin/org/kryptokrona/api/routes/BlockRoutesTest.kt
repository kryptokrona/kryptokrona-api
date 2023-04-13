package org.kryptokrona.api.routes;

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Before
import org.kryptokrona.api.models.Block
import org.kryptokrona.api.services.block.BlockServiceImpl
import org.kryptokrona.api.utils.jsonObjectMapper
import org.kryptokrona.sdk.http.model.block.Blocks
import org.ktorm.entity.Entity
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BlockRoutesTest {

    private val logger = LoggerFactory.getLogger("BlockRoutesTest")

    private val blockService: BlockServiceImpl = BlockServiceImpl()

    private val baseEndpoint: String = "/api/v1/blocks"

    @Before
    fun beforeEach() = testApplication {
        val block = Block {
            id = 1
            hash = "hash"
            time = 10000
            difficulty = 1.0F
            reward = 1.0F
            createdAt = now()
        }
        blockService.getById(block.id).let {
            if (it == null) {
                blockService.save(block)
                logger.info("Block with id: ${block.id} saved")
            } else {
                logger.info("Block with id: ${it.id} already exists")
            }
        }
    }

    @Test
    fun `can get all blocks`() = testApplication {
        client.get(baseEndpoint).apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

    @Test
    fun `can get a block by id`() = testApplication {
        val blockId = 1
        val response = client.get("$baseEndpoint/$blockId").body<Block>()

        assertTrue { blockId.toLong() == response.id }
    }
}