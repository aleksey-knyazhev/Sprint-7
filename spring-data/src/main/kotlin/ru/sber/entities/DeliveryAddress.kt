package ru.sber.entities

import javax.persistence.*

@Entity
@Table(name="deliveryaddress")
class DeliveryAddress(
    @Id
    @GeneratedValue
    var id: Long = 0,

    @Basic
    var address: String
)
