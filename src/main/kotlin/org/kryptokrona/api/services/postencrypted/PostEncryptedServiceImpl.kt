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

package org.kryptokrona.api.services.postencrypted

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.kryptokrona.api.models.PostEncrypted
import org.kryptokrona.api.models.PostsEncrypted
import org.kryptokrona.api.models.postsencrypted
import org.kryptokrona.api.plugins.DatabaseFactory.db
import org.ktorm.dsl.*
import org.ktorm.entity.add
import org.ktorm.entity.count
import org.ktorm.entity.find
import org.ktorm.entity.removeIf
import org.slf4j.LoggerFactory

class PostEncryptedServiceImpl : PostEncryptedService {

    private val logger = LoggerFactory.getLogger("PostEncryptedServiceImpl")

    override suspend fun getAll(size: Int, page: Int): List<PostEncrypted> = withContext(Dispatchers.IO) {
        db.from(PostsEncrypted)
            .select()
            .offset((page - 1) * size)
            .limit(size)
            .map { row -> PostsEncrypted.createEntity(row) }
    }

    override suspend fun getById(id: Long): PostEncrypted? = withContext(Dispatchers.IO) {
        this.runCatching {
            db.postsencrypted.find { it.id eq id }
        }.onFailure {
            logger.error("Error finding encrypted post with hash: $id", it)
        }.getOrNull()
    }

    override suspend fun save(postEncrypted: PostEncrypted): Unit = withContext(Dispatchers.IO) {
        this.runCatching {
            db.postsencrypted.add(postEncrypted)
        }.onFailure {
            logger.error("Error saving encrypted post with hash: ${postEncrypted.txHash}", it)
        }
    }

    override suspend fun delete(id: Long): Unit = withContext(Dispatchers.IO) {
        this.runCatching {
            db.postsencrypted.removeIf { it.id eq 2 }
        }.onFailure {
            logger.error("Error deleting encrypted post with id: $id", it)
        }
    }

    override suspend fun existsByTxHash(txHash: String): Boolean = withContext(Dispatchers.IO) {
        try {
            db.postsencrypted.find { it.txHash eq txHash } != null
        } catch (e: Exception) {
            logger.error("Error finding encrypted post with txBox: $txHash", e)
            false
        }
    }

    override suspend fun getTotalCount(): Int = withContext(Dispatchers.IO) {
        this.runCatching {
            db.postsencrypted.count()
        }.onFailure {
            logger.error("Error getting total encrypted posts count", it)
        }.getOrNull() ?: 0
    }

}