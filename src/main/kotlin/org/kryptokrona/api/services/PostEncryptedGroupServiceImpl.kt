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

package org.kryptokrona.api.services

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.kryptokrona.api.models.PostEncryptedGroup
import org.kryptokrona.api.models.PostEncryptedGroups
import org.kryptokrona.api.models.postencryptedgroups
import org.kryptokrona.api.plugins.DatabaseFactory.db
import org.ktorm.dsl.*
import org.ktorm.entity.add
import org.ktorm.entity.count
import org.ktorm.entity.find

class PostEncryptedGroupServiceImpl : PostEncryptedGroupService {

    override fun getAll(size: Int, page: Int): List<PostEncryptedGroup> {
        return db.from(PostEncryptedGroups)
            .select()
            .offset((page - 1) * size)
            .limit(size)
            .map { row -> PostEncryptedGroups.createEntity(row) }
    }

    override fun getById(id: Long): PostEncryptedGroup? {
        return db.postencryptedgroups.find { it.id eq id }
    }

    override fun save(postEncryptedGroup: PostEncryptedGroup) {
        db.postencryptedgroups.add(postEncryptedGroup)
    }

    override fun delete(id: Long) {
        db.delete(PostEncryptedGroups) { it.id eq id }
    }

    override suspend fun existsByTxSb(txSb: String): Boolean = withContext(Dispatchers.IO) {
        db.postencryptedgroups.find { it.txSb eq txSb } != null
    }

    override fun getTotalCount(): Int {
        return db.postencryptedgroups.count()
    }

}