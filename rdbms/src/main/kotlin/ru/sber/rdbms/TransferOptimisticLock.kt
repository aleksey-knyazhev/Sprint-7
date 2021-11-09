package ru.sber.rdbms

import java.sql.SQLException

class OptimisticLockException(message: String) : SQLException(message)

class TransferOptimisticLock {
    fun transfer(accountId1: Long, accountId2: Long, amount: Long) {
        TODO()
    }
}
