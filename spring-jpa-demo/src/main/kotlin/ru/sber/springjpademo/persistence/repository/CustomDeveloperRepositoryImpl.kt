package ru.sber.springjpademo.persistence.repository

import org.springframework.stereotype.Repository
import ru.sber.springjpademo.persistence.entity.Developer
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class CustomDeveloperRepositoryImpl(
    @PersistenceContext
    private val entityManager: EntityManager
) : CustomDeveloperRepository {
    override fun findAllDevelopers(): List<Developer> =
        entityManager.createQuery("""from Developer""").resultList as List<Developer>
}