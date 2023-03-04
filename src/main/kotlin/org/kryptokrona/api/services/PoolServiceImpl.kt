package org.kryptokrona.api.services

import org.kryptokrona.api.models.Pool
import org.kryptokrona.api.models.Pools
import org.kryptokrona.api.models.pools
import org.kryptokrona.api.plugins.DatabaseConnection
import org.ktorm.dsl.*
import org.ktorm.entity.add
import org.ktorm.entity.count
import org.ktorm.entity.find

class PoolServiceImpl : PoolService {

    private val db = DatabaseConnection.database

    override fun getAll(size: Int, page: Int): List<Pool> {
        return db.from(Pools)
            .select()
            .offset((page - 1) * size)
            .limit(size)
            .map { row -> Pools.createEntity(row) }
    }

    override fun getById(id: Long): Pool? {
        return db.pools.find { it.id eq id }
    }

    override fun save(pool: Pool) {
        db.pools.add(pool)
    }

    override fun delete(id: Long) {
        db.delete(Pools) { it.id eq id }
    }

    override fun getTotalCount(): Int {
        return db.pools.count()
    }

}