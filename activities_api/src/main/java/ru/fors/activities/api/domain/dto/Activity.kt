package ru.fors.activities.api.domain.dto

/**
 * Created by 23alot on 23.02.2020.
 */
data class Activity(
    val endDate: String,
    val id: Long,
    val lead: LeadItem,
    val name: String,
    val startDate: String
)

data class LeadItem(
    val firstName: String,
    val id: Long,
    val lastName: String,
    val middleName: String,
    val position: String,
    val skills: List<String>,
    val subdivision: String
)