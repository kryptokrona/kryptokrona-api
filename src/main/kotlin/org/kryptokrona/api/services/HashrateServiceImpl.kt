package org.kryptokrona.api.services

import org.kryptokrona.api.models.*
import org.kryptokrona.api.models.blocks
import org.kryptokrona.api.plugins.DatabaseConnection
import org.ktorm.dsl.*
import org.ktorm.entity.add
import org.ktorm.entity.count
import org.ktorm.entity.find

class HashrateServiceImpl : HashrateService {

    private val db = DatabaseConnection.database

    override fun getAll(size: Int, page: Int): List<Hashrate> {
        return db.from(Hashrates)
        .select()
        .offset((page - 1) * size)
        .limit(size)
        .map { row -> Hashrates.createEntity(row) }
    }

    override fun getById(id: Long): Hashrate? {
        return db.hashrates.find { it.id eq id }
    }

    override fun save(hashrate: Hashrate) {
        db.hashrates.add(hashrate)
    }

    override fun delete(id: Long) {
        db.delete(Hashrates) { it.id eq id }
    }

    override fun getTotalCount(): Int {
        return db.hashrates.count()
    }

}