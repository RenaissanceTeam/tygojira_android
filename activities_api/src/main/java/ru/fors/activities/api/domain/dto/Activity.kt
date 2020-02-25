package ru.fors.activities.api.domain.dto

/**
 * Created by 23alot on 23.02.2020.
 */
data class Activity(
    val id: Long,
    val name: String,
    val startDate: String,
    val endDate: String
)