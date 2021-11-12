package ru.sber.entities

import org.hibernate.annotations.Check
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Check(constraints = "amount >= 0")
class Invoice(
    @Id
    @GeneratedValue
    var id: Long = 0,

    @Basic
    var date: LocalDateTime,

    @Basic
    var amount: Long = 0,

    @ManyToOne(
        cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER
    )
    var customer: Customer? = null,

    // Будем считать, что второй раз по одному адресу - товар никто в здравом уме не купит.
    // Входную дверь продаем, например. Зачем их две, в одной квартире?
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "deliveryaddress_id")
    var deliveryAddress: DeliveryAddress

)
