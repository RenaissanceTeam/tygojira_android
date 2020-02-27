package ru.fors.employees.presentation.viewmodel

import ru.fors.user.api.domain.dto.Employee

/**
 * Created by 23alot on 26.02.2020.
 */
typealias EmployeesPartialViewState = (EmployeesViewState) -> EmployeesViewState

object EmployeesPartialViewStates {

    fun onQueryChanged(query: String): EmployeesPartialViewState = { previousViewState ->
        previousViewState.copy(
            query = query,
            showEmployees = onQuery(previousViewState.employees, query)
        )
    }

    fun onResetSelectedEmployee(): EmployeesPartialViewState = { previousViewState ->
        previousViewState.copy(
            selectedEmployee = null
        )
    }

    fun onEmployeeSelected(position: Int): EmployeesPartialViewState = { previousViewState ->
        previousViewState.copy(
            selectedEmployee = previousViewState.showEmployees[position]
        )
    }

    fun onEmployeesLoaded(employees: List<Employee>): EmployeesPartialViewState = { previousViewState ->
        val newEmployees = mutableListOf<Employee>()
        newEmployees.addAll(previousViewState.employees)
        newEmployees.addAll(employees)
        previousViewState.copy(
            employees = newEmployees,
            showEmployees = onQuery(newEmployees, previousViewState.query)
        )
    }

    private fun onQuery(employees: List<Employee>, query: String): List<Employee> {
        return employees.filter { employee ->
            val searchRow = "${employee.firstName}${employee.middleName}${employee.lastName}"
            val queries = query.split(" ", ignoreCase = true)
            for (q in queries) {
                if (!searchRow.contains(q, ignoreCase = true)) return@filter false
            }
            return@filter true
        }
    }
}