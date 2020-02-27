package ru.fors.employees.presentation.viewmodel

import ru.fors.user.api.domain.dto.Employee

/**
 * Created by 23alot on 26.02.2020.
 */
data class EmployeesViewState(
    val employees: List<Employee> = listOf(),
    val showEmployees: List<Employee> = listOf(),
    val query: String = "",
    val selectedEmployee: Employee? = null
)