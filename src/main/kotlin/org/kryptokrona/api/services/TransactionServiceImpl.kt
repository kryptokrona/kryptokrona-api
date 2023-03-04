package org.kryptokrona.api.services

import org.kryptokrona.api.models.Transaction

class TransactionServiceImpl : TransactionService {

    override fun getAll(size: Int, page: Int): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun getById(id: Long): Transaction? {
        TODO("Not yet implemented")
    }

    override fun save(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long) {
        TODO("Not yet implemented")
    }

    override fun getTotalCount(): Int {
        TODO("Not yet implemented")
    }

}