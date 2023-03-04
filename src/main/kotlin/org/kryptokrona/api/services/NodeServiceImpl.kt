package org.kryptokrona.api.services

import org.kryptokrona.api.models.*
import org.kryptokrona.api.plugins.DatabaseConnection
import org.ktorm.dsl.*
import org.ktorm.entity.add
import org.ktorm.entity.count
import org.ktorm.entity.find

class NodeServiceImpl : NodeService {

    private val db = DatabaseConnection.database

    override fun getAll(size: Int, page: Int): List<Node> {
        return db.from(Nodes)
        .select()
        .offset((page - 1) * size)
        .limit(size)
        .map { row -> Nodes.createEntity(row) }
    }

    override fun getById(id: Long): Node? {
        return db.nodes.find { it.id eq id }
    }

    override fun save(node: Node) {
        db.nodes.add(node)
    }

    override fun delete(id: Long) {
        db.delete(Nodes) { it.id eq id }
    }

    override fun getTotalCount(): Int {
        return db.nodes.count()
    }

}