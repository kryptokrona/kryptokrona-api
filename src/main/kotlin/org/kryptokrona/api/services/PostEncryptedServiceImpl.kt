package org.kryptokrona.api.services

import org.kryptokrona.api.models.*
import org.kryptokrona.api.plugins.DatabaseConnection
import org.ktorm.dsl.*
import org.ktorm.entity.add
import org.ktorm.entity.count
import org.ktorm.entity.find

class PostEncryptedServiceImpl : PostEncryptedService {

    private val db = DatabaseConnection.database

    override fun getAll(size: Int, page: Int): List<PostEncrypted> {
        return db.from(PostsEncrypted)
        .select()
        .offset((page - 1) * size)
        .limit(size)
        .map { row -> PostsEncrypted.createEntity(row) }
    }

    override fun getById(id: Long): PostEncrypted? {
        return db.postsencrypted.find { it.id eq id }
    }

    override fun save(postEncrypted: PostEncrypted) {
        db.postsencrypted.add(postEncrypted)
    }

    override fun delete(id: Long) {
        db.delete(PostsEncrypted) { it.id eq id }
    }

    override fun getTotalCount(): Int {
        return db.transactions.count()
    }

}