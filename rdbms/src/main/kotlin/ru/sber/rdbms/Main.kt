package ru.sber.rdbms

fun main() {
//    val transferConstraint = TransferConstraint()
//    transferConstraint.transfer(1, 2, 100)

//    val transferOptimisticLock = TransferOptimisticLock()
//    transferOptimisticLock.transfer(1, 2, 100)

    val transferPessimisticLock = TransferPessimisticLock()
    transferPessimisticLock.transfer(1, 2, 100)
}