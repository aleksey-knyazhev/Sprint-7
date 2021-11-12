package ru.sber.entities

import entities.Customer
import ru.sber.entities.Customer
import org.hibernate.annotations.Check
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="invoice_spring_data")
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
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null,

    // Будем считать, что второй раз по одному адресу - товар никто в здравом уме не купит.
    // Входную дверь продаем, например. Зачем их две, в одной квартире?
    @OneToOne(cascade = [CascadeType.ALL])
    var deliveryAddress: DeliveryAddress

)
