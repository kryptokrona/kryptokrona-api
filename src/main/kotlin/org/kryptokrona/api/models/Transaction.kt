// Copyright (c) 2023-2023, The Kryptokrona Developers
//
// Written by Marcus Cvjeticanin
//
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without modification, are
// permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice, this list of
//    conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright notice, this list
//    of conditions and the following disclaimer in the documentation and/or other
//    materials provided with the distribution.
//
// 3. Neither the name of the copyright holder nor the names of its contributors may be
//    used to endorse or promote products derived from this software without specific
//    prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
// EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
// THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
// PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
// STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
// THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package org.kryptokrona.api.models

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.*
import java.time.LocalDateTime

interface Transaction : Entity<Transaction> {
    companion object : Entity.Factory<Transaction>()
    val id: Int
    var hash: String
    var time: Long
    var confirmations: Int
    var paymentID: String
    var amount: Long
    var fee: Long
    var mixin: Int
    var size: Long
    var blockId: Block
    var createdAt: LocalDateTime
    //TODO: add one-to-many relationship to Output

}

object Transactions : Table<Transaction>("transactions") {
    val id = int("id").primaryKey().bindTo { it.id }
    val hash = varchar("hash").bindTo { it.hash }
    val time = long("time").bindTo { it.time }
    val confirmations = int("confirmations").bindTo { it.confirmations }
    val paymentID = varchar("payment_id").bindTo { it.paymentID }
    val amount = long("amount").bindTo { it.amount }
    val fee = long("fee").bindTo { it.fee }
    val mixin = int("mixin").bindTo { it.mixin }
    val size = long("size").bindTo { it.size }
    val blockId = int("block_id").references(Blocks) { it.blockId }
    val createdAt = datetime("created_at").bindTo { it.createdAt }
}

val Database.transactions get() = this.sequenceOf(Transactions)