package ru.sber

import entities.Customer
import entities.CustomerType
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import ru.sber.entities.DeliveryAddress
import ru.sber.entities.Invoice
import ru.sber.repositories.CustomCustomerRepository
import java.time.LocalDateTime

@SpringBootApplication
class SpringDataApplication(
    private val customCustomerRepository: CustomCustomerRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val customer1 = Customer(
            taxpayerNumber = 7727563778,
            customerType = CustomerType.COMPANY,
            name = "ООО Рога и Копыта",
            invoices = mutableListOf(
                Invoice(date = LocalDateTime.now(), amount = 100, deliveryAddress = DeliveryAddress(address = "Площадь Красная, 1, оф. 1")),
                Invoice(date = LocalDateTime.now(), amount = 200, deliveryAddress = DeliveryAddress(address = "Площадь Красная, 1, оф. 2")),
            )
        )

        val customer2 = Customer(
            taxpayerNumber = 510903990454,
            customerType = CustomerType.ENTREPRENEUR,
            name = "Иванов Иван Иванович",
            invoices = mutableListOf(
                Invoice(date = LocalDateTime.now(), amount = 10, deliveryAddress = DeliveryAddress(address = "На деревню, дедушке")),
                Invoice(date = LocalDateTime.now(), amount = 20, deliveryAddress = DeliveryAddress(address = "На деревню, бабушке")),
            )
        )
        customCustomerRepository.saveAll(listOf(customer1, customer2))

        val resultFindByTaxpayerNumber = customCustomerRepository.findByTaxpayerNumber(510903990454)
        println("Должно быть найдено либо один покупатель, либо ни одного вообще: $resultFindByTaxpayerNumber")

        val resultFindByCustomerType = customCustomerRepository.findByCustomerType(CustomerType.ENTREPRENEUR)
        println("Должны быть найдены все индивидуальные предприниматели (если таковые имеются): $resultFindByCustomerType")
    }
}

fun main(args: Array<String>) {
    runApplication<SpringDataApplication>(*args)
}
