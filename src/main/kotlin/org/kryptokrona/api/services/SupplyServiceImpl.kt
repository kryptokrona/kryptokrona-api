package org.kryptokrona.api.services

import org.kryptokrona.api.models.Supplies
import org.kryptokrona.api.models.Supply
import org.kryptokrona.api.models.supplies
import org.kryptokrona.api.plugins.DatabaseFactory
import org.ktorm.dsl.*
import org.ktorm.entity.add
import org.ktorm.entity.count
import org.ktorm.entity.find

class SupplyServiceImpl : SupplyService {

    private val db = DatabaseFactory.database

    override fun getAll(size: Int, page: Int): List<Supply> {
        return db.from(Supplies)
            .select()
            .offset((page - 1) * size)
            .limit(size)
            .map { row -> Supplies.createEntity(row) }
    }

    override fun getById(id: Long): Supply? {
        return db.supplies.find { it.id eq id }
    }

    override fun save(supply: Supply) {
        db.supplies.add(supply)
    }

    override fun delete(id: Long) {
        db.delete(Supplies) { it.id eq id }
    }

    override fun getTotalCount(): Int {
        return db.supplies.count()
    }

}