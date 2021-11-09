package ru.sber.rdbms

import java.sql.DriverManager
import java.sql.SQLException

class OptimisticLockException(message: String) : SQLException(message)

class TransferOptimisticLock {
    private val connection = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/db",
        "postgres",
        "postgres"
    )

    fun transfer(accountId1: Long, accountId2: Long, amount: Long) {
        connection.use { conn ->
            try {
                val prepareStatement1 = conn.prepareStatement("update account3 set amount = amount + ? where id = ?;")
                prepareStatement1.use { statement ->
                    statement.setLong(1, amount)
                    statement.setLong(2, accountId1)
                    statement.executeUpdate()
                }


            } catch (exception: OptimisticLockException) {
                println(exception.message)
            }
        }
    }
}
