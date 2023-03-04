package org.kryptokrona.api.services

import org.kryptokrona.api.models.*
import org.kryptokrona.api.plugins.DatabaseConnection
import org.ktorm.dsl.*
import org.ktorm.entity.add
import org.ktorm.entity.count
import org.ktorm.entity.find

class OutputServiceImpl : OutputService {

    private val db = DatabaseConnection.database

    override fun getAll(size: Int, page: Int): List<Output> {
        return db.from(Outputs)
        .select()
        .offset((page - 1) * size)
        .limit(size)
        .map { row -> Outputs.createEntity(row) }
    }

    override fun getById(id: Long): Output? {
        return db.outputs.find { it.id eq id }
    }

    override fun save(output: Output) {
        db.outputs.add(output)
    }

    override fun delete(id: Long) {
        db.delete(Outputs) { it.id eq id }
    }

    override fun getTotalCount(): Int {
        return db.outputs.count()
    }

}