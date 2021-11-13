package ru.sber.springjpademo.persistence.repository

import ru.sber.springjpademo.persistence.entity.Developer

interface CustomDeveloperRepository {
    fun findAllDevelopers(): List<Developer>
}