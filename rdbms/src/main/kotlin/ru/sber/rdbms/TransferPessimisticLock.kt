package ru.sber.rdbms

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class TransferPessimisticLock {
    private val connection = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/db",
        "postgres",
        "postgres"
    )

    fun blockDebitAccount(conn: Connection, accountId: Long) {
        val prepareStatement1 = conn.prepareStatement("select * from account3 where id = ? for update")
        prepareStatement1.use { statement ->
            statement.setLong(1, accountId)
            statement.executeQuery()
        }
    }

    fun transfer(accountId1: Long, accountId2: Long, amount: Long) {
        val array = arrayOf(accountId1, accountId2)
        array.sort()
        val minId = array[0]

            connection.use { conn ->
                val autoCommit = conn.autoCommit
                try {
                    conn.autoCommit = false

                    if (accountId1 != minId)
                        blockDebitAccount(conn, accountId2)

                    // Block credit account
                    val prepareStatement1 = conn.prepareStatement("select * from account3 where id = ? for update")
                    prepareStatement1.use { statement ->
                        statement.setLong(1, accountId1)

                        statement.executeQuery().use {
                            it.next()
                            if (it.getInt("amount") - amount < 0)
                                throw OptimisticLockException("Недостаточно средств")
                        }
                    }

                    if (accountId1 == minId)
                        blockDebitAccount(conn, accountId2)

                    val prepareStatement2 = conn.prepareStatement("update account3 set amount = amount - ? where id = ?")
                    prepareStatement2.use { statement ->
                        statement.setLong(1, amount)
                        statement.setLong(2, accountId1)
                        statement.executeUpdate()
                    }

                    val prepareStatement3 = conn.prepareStatement("update account3 set amount = amount + ? where id = ?")
                    prepareStatement3.use { statement ->
                        statement.setLong(1, amount)
                        statement.setLong(2, accountId2)
                        statement.executeUpdate()
                    }

                    conn.commit()
                } catch (exception: SQLException) {
                    println(exception.message)
                    exception.printStackTrace()
                    conn.rollback()
                } finally {
                    conn.autoCommit = autoCommit
                }
            }



    }
}
