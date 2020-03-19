package ru.fors.user.api.domain.dto

/**
 * Created by 23alot on 22.02.2020.
 */
data class Employee(
    val firstName: String,
    val id: Long,
    val lastName: String,
    val middleName: String,
    val position: String,
    val skills: List<String>,
    val subdivision: String
)