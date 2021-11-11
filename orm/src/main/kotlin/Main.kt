import dao.CustomerDAO
import entities.Customer
import entities.CustomerType
import entities.DeliveryAddress
import entities.Invoice
import org.hibernate.cfg.Configuration
import java.time.LocalDateTime

fun main() {
    val sessionFactory = Configuration().configure()
        .addAnnotatedClass(Invoice::class.java)
        .addAnnotatedClass(Customer::class.java)
        .addAnnotatedClass(DeliveryAddress::class.java)
        .buildSessionFactory()

    sessionFactory.use {
        val customerDao = CustomerDAO(it)

        val customer1 = Customer(
            taxpayerNumber = 7727563778,
            customerType = CustomerType.COMPANY,
            name = "ООО Рога и Копыта",
            invoices = mutableListOf(
                Invoice(date = LocalDateTime.now(), amount = 100, deliveryAddress = DeliveryAddress(address = "Площадь Красная, 1, оф. 1")),
                Invoice(date = LocalDateTime.now(), amount = 200, deliveryAddress = DeliveryAddress(address = "Площадь Красная, 1, оф. 2")),
            )
        )
        customerDao.save(customer1)


        val customer2 = Customer(
            taxpayerNumber = 510903990454,
            customerType = CustomerType.ENTREPRENEUR,
            name = "Иванов Иван Иванович",
            invoices = mutableListOf(
                Invoice(date = LocalDateTime.now(), amount = 10, deliveryAddress = DeliveryAddress(address = "На деревню, дедушке")),
                Invoice(date = LocalDateTime.now(), amount = 20, deliveryAddress = DeliveryAddress(address = "На деревню, бабушке")),
            )
        )
        customerDao.save(customer2)

        val foundCustomer1 = customerDao.find(customer1.id)
        println("Найден 1-й покупатель: $foundCustomer1\n")
        customerDao.delete(customer1.id)
        val allCustomers = customerDao.findAll()
        println("Полный список покупателей: $allCustomers")
    }
}