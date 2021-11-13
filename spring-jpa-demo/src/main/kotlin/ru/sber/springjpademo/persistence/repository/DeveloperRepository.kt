package ru.sber.springjpademo.persistence.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.sber.springjpademo.persistence.entity.Developer
import ru.sber.springjpademo.persistence.entity.Specialty

@Repository
interface DeveloperRepository : CrudRepository<Developer, Long> {
    //auto
    fun findBySpecialty(specialty: Specialty): List<Developer>

    //manual
    @Query("SELECT d FROM Developer d where d.experience = :exp")
    fun findSkilled(@Param("exp") experience: Int): List<Developer>
}