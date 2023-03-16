package org.kryptokrona.api.services

import org.kryptokrona.api.models.Output
import org.kryptokrona.api.models.Outputs
import org.kryptokrona.api.models.outputs
import org.kryptokrona.api.plugins.DatabaseFactory
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.add
import org.ktorm.entity.count
import org.ktorm.entity.find

class OutputServiceImpl : OutputService {

    private val dataSource = DatabaseFactory.setupDataSource()

    private val db = Database.connect(dataSource)

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