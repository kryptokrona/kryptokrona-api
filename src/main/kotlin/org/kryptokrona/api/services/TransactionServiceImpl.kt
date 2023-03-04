package org.kryptokrona.api.services

import org.kryptokrona.api.models.Transaction
import org.kryptokrona.api.models.Transactions
import org.kryptokrona.api.models.transactions
import org.kryptokrona.api.plugins.DatabaseConnection
import org.ktorm.dsl.*
import org.ktorm.entity.add
import org.ktorm.entity.count
import org.ktorm.entity.find

class TransactionServiceImpl : TransactionService {

    private val db = DatabaseConnection.database

    override fun getAll(size: Int, page: Int): List<Transaction> {
        return db.from(Transactions)
        .select()
        .offset((page - 1) * size)
        .limit(size)
        .map { row -> Transactions.createEntity(row) }
    }

    override fun getById(id: Long): Transaction? {
        return db.transactions.find { it.id eq id }
    }

    override fun save(transaction: Transaction) {
        db.transactions.add(transaction)
    }

    override fun delete(id: Long) {
        db.delete(Transactions) { it.id eq id }
    }

    override fun getTotalCount(): Int {
        return db.transactions.count()
    }

}