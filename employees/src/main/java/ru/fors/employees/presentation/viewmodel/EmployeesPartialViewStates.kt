package ru.fors.employees.presentation.viewmodel

import ru.fors.user.api.domain.dto.Employee

/**
 * Created by 23alot on 26.02.2020.
 */
typealias EmployeesPartialViewState = (EmployeesViewState) -> EmployeesViewState

object EmployeesPartialViewStates {

    fun onResetSelectedEmployee(): EmployeesPartialViewState = { previousViewState ->
        previousViewState.copy(
            selectedEmployee = null
        )
    }

    fun onEmployeeSelected(employee: Employee): EmployeesPartialViewState = { previousViewState ->
        previousViewState.copy(
            selectedEmployee = employee
        )
    }

    fun onEmployeesLoaded(employees: List<Employee>): EmployeesPartialViewState = { previousViewState ->
        val newEmployees = mutableListOf<Employee>()
        newEmployees.addAll(previousViewState.employees)
        newEmployees.addAll(employees)
        previousViewState.copy(
            employees = newEmployees
        )
    }
}