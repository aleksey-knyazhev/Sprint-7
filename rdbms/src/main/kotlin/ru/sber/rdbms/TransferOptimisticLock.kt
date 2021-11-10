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
        var oldVersion: Int

        connection.use { conn ->
            val autoCommit = conn.autoCommit
            try {
                conn.autoCommit = false

                val prepareStatement1 = conn.prepareStatement("select * from account3 where id = ?")
                prepareStatement1.use { statement ->
                    statement.setLong(1, accountId1)

                    statement.executeQuery().use {
                        it.next()
                        if (it.getInt("amount") - amount < 0)
                            throw OptimisticLockException("Недостаточно средств")
                        oldVersion = it.getInt("version")
                    }
                }

                val prepareStatement2 = conn.prepareStatement("update account3 set amount = amount - ?, version = version + 1 where id = ? and version = ?")
                prepareStatement2.use { statement ->
                    statement.setLong(1, amount)
                    statement.setLong(2, accountId1)
                    statement.setInt(3, oldVersion)

                    if (prepareStatement2.executeUpdate() == 0)
                        throw OptimisticLockException("Оптимистичная блокировка")
                }

                val prepareStatement3 = conn.prepareStatement("select * from account3 where id = ?")
                prepareStatement3.use { statement ->
                    statement.setLong(1, accountId2)

                    statement.executeQuery().use {
                        it.next()
                        oldVersion = it.getInt("version")
                    }
                }

                val prepareStatement4 = conn.prepareStatement("update account3 set amount = amount + ?, version = version + 1 where id = ? and version = ?")
                prepareStatement4.use { statement ->
                    statement.setLong(1, amount)
                    statement.setLong(2, accountId2)
                    statement.setInt(3, oldVersion)

                    if (prepareStatement4.executeUpdate() == 0)
                        throw OptimisticLockException("Оптимистичная блокировка")

                }

                conn.commit()
            } catch (exception: OptimisticLockException) {
                println(exception.message)
                exception.printStackTrace()
                conn.rollback()
            } finally {
                conn.autoCommit = autoCommit
            }
        }
    }
}
