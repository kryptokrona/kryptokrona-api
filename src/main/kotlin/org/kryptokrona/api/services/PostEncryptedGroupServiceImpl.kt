package org.kryptokrona.api.services

import org.kryptokrona.api.models.PostEncryptedGroup
import org.kryptokrona.api.models.PostEncryptedGroups
import org.kryptokrona.api.models.postencryptedgroups
import org.kryptokrona.api.plugins.DatabaseFactory
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.add
import org.ktorm.entity.count
import org.ktorm.entity.find

class PostEncryptedGroupServiceImpl : PostEncryptedGroupService {

    private val dataSource = DatabaseFactory.setupDataSource()

    private val db = Database.connect(dataSource)

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

    override fun existsByTxSb(txSb: String): Boolean {
        db.postencryptedgroups.find { it.txSb eq txSb }?.let {
            return true
        }

        return false
    }

    override fun getTotalCount(): Int {
        return db.postencryptedgroups.count()
    }

}