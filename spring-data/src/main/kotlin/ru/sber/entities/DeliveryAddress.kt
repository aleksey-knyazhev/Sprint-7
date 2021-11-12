package ru.sber.entities

import javax.persistence.*

@Entity
class DeliveryAddress(
    @Id
    @GeneratedValue
    var id: Long = 0,

    @Basic
    var address: String
)
