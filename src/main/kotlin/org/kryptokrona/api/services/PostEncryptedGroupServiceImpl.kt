package org.kryptokrona.api.services

import org.kryptokrona.api.models.PostEncryptedGroup
import org.kryptokrona.api.models.PostEncryptedGroups
import org.kryptokrona.api.models.postencryptedgroups
import org.kryptokrona.api.plugins.DatabaseConnection
import org.ktorm.dsl.*
import org.ktorm.entity.add
import org.ktorm.entity.count
import org.ktorm.entity.find

class PostEncryptedGroupServiceImpl : PostEncryptedGroupService {

    private val db = DatabaseConnection.database

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

    override fun getTotalCount(): Int {
        return db.postencryptedgroups.count()
    }

}