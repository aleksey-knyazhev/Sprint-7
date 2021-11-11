package dao

import entities.Customer
import org.hibernate.SessionFactory

class CustomerDAO(
    private val sessionFactory: SessionFactory
) {
    fun save(customer: Customer) {
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            session.persist(customer)
            session.transaction.commit()
        }
    }

    fun find(id: Long): Customer? {
        val result: Customer?
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            result = session.get(Customer::class.java, id)
            session.transaction.commit()
        }
        return result
    }

    fun findAll(): List<Customer> {
        val result: List<Customer>
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            result = session.createQuery("from Customer").list() as List<Customer>
            session.transaction.commit()
        }
        return result
    }

    fun delete(id: Long) {
        val customer: Customer?
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            customer = session.get(Customer::class.java, id)
            session.delete(customer)
            session.transaction.commit()
        }
        return
    }
}