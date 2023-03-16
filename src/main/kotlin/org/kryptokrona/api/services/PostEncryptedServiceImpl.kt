package org.kryptokrona.api.services

import org.kryptokrona.api.models.PostEncrypted
import org.kryptokrona.api.models.PostsEncrypted
import org.kryptokrona.api.models.postsencrypted
import org.kryptokrona.api.plugins.DatabaseFactory
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.add
import org.ktorm.entity.count
import org.ktorm.entity.find

class PostEncryptedServiceImpl : PostEncryptedService {

    private val dataSource = DatabaseFactory.setupDataSource()

    private val db = Database.connect(dataSource)

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

    override fun existsByTxBox(txBox: String): Boolean {
        db.postsencrypted.find { it.txBox eq txBox }?.let {
            return true
        }

        return false
    }

    override fun getTotalCount(): Int {
        return db.postsencrypted.count()
    }

}