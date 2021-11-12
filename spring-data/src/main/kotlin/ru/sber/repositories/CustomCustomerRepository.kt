package ru.sber.repositories

//import entities.Customer
//import entities.CustomerType
import ru.sber.entities.Customer
import ru.sber.entities.CustomerType
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CustomCustomerRepository : CrudRepository<Customer, Long> {
    //auto
    fun findByTaxpayerNumber(taxpayerNumber: Long): List<Customer>

    //manual
    @Query("SELECT c FROM Customer c where c.customerType = :exp")
    fun findByCustomerType(@Param("exp") customerType: CustomerType): List<Customer>
}