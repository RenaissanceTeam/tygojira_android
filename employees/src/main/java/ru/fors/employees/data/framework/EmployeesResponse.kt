package ru.fors.employees.data.framework

import ru.fors.user.api.domain.dto.Employee

/**
 * Created by 23alot on 26.02.2020.
 */
data class EmployeesResponse(
    val currentPage: Int,
    val items: List<Employee>,
    val totalItems: Int,
    val totalPages: Int
)