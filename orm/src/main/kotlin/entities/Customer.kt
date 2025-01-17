package entities

import org.hibernate.annotations.Check
import org.hibernate.annotations.NaturalId
import javax.persistence.*

@Entity
@Check(constraints = "taxpayerNumber >= 0")
class Customer(
    @Id
    @GeneratedValue
    var id: Long = 0,

    @NaturalId
    @Column(length = 12)
//    @Column(length = 12, name = "taxpayer_number")
    var taxpayerNumber: Long,

    @Enumerated(value = EnumType.STRING)
//    @Column(name = "customer_type")
    var customerType: CustomerType,

    @Basic
    var name: String,

    @OneToMany(
        cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER
    )
    var invoices: MutableList<Invoice> = mutableListOf()
) {
    override fun toString(): String {
        return "$name, ${customerType.type}, ИНН $taxpayerNumber"
    }

    fun newSale(invoice: Invoice) {
        invoices.add(invoice)
        invoice.customer = this
    }
}

enum class CustomerType(val type: String) {
    COMPANY("Юридическое лицо"),
    ENTREPRENEUR("Индивидуальный предприниматель")
}