package ru.sber.springjpademo.persistence.repository

import org.springframework.data.repository.PagingAndSortingRepository
import ru.sber.springjpademo.persistence.entity.Developer

interface DeveloperPagingRepository: PagingAndSortingRepository<Developer, Long>