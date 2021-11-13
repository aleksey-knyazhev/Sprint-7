package ru.sber.springjpademo

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.domain.PageRequest
import ru.sber.springjpademo.persistence.entity.Developer
import ru.sber.springjpademo.persistence.entity.Specialty
import ru.sber.springjpademo.persistence.repository.CustomDeveloperRepository
import ru.sber.springjpademo.persistence.repository.DeveloperPagingRepository
import ru.sber.springjpademo.persistence.repository.DeveloperRepository

@SpringBootApplication
class SpringJpaDemoApplication(
    private val developerRepository: DeveloperRepository,
    private val developerPagingRepository: DeveloperPagingRepository,
    private val customDeveloperRepository: CustomDeveloperRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
//        val kotlinDev = Developer(firstName = "Вася", lastName = "Иванов", specialty = Specialty.KOTLIN, experience = 1)
//        val kotlinDev2 = Developer(firstName = "Вася2", lastName = "Иванов", specialty = Specialty.KOTLIN, experience = 2)
//        val javaDev1 = Developer(firstName = "Вася3", lastName = "Иванов", specialty = Specialty.JAVA, experience = 3)
//        val javaDev2 = Developer(firstName = "Вася4", lastName = "Иванов", specialty = Specialty.JAVA, experience = 2)
//
//        developerRepository.saveAll(listOf(kotlinDev, kotlinDev2, javaDev1, javaDev2))

//        val kotlinDevs = developerRepository.findBySpecialty(Specialty.KOTLIN)
//        val skilledDevs = developerRepository.findSkilled(2)

//        println(kotlinDevs)
//
//        println(skilledDevs)
//        val pageResult = developerPagingRepository.findAll(PageRequest.of(1, 2))
//        println("Total elements: ${pageResult.totalElements}")
//        println("Total pages: ${pageResult.totalPages}")
//        println("page 1: ${pageResult.content}")

        val resultAll = customDeveloperRepository.findAllDevelopers()
        println(resultAll)
    }
}

fun main(args: Array<String>) {
    runApplication<SpringJpaDemoApplication>(*args)
}
