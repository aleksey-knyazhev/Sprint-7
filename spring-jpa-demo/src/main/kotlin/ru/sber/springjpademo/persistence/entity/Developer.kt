package ru.sber.springjpademo.persistence.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "spring_data_jpa_developers")
class Developer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "first_name")
    var firstName: String,
    @Column(name = "last_name")
    var lastName: String,
    @Enumerated(value = EnumType.STRING)
    var specialty: Specialty,
    var experience: Int
) {
    override fun toString(): String {
        return "Developer(id=$id, firstName='$firstName', lastName='$lastName', specialty=$specialty, experience=$experience)"
    }
}

enum class Specialty {
    KOTLIN,
    JAVA
}