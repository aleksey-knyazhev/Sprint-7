package ru.sber.entities

import javax.persistence.*

@Entity
@Table(name="delivery_address_spring_data")
class DeliveryAddress(
    @Id
    @GeneratedValue
    var id: Long = 0,

    @Basic
    var address: String
)
