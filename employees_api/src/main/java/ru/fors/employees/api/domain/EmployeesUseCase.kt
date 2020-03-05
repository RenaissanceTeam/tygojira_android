package ru.fors.employees.api.domain

import kotlinx.coroutines.flow.Flow
import ru.fors.user.api.domain.dto.Employee

/**
 * Created by 23alot on 26.02.2020.
 */
interface EmployeesUseCase {
    sealed class EmployeesEvent {
        data class Employees(val employees: List<Employee>): EmployeesEvent()

        object NotExist : EmployeesEvent()

        data class Error(val throwable: Throwable) : EmployeesEvent()
    }

    suspend operator fun invoke(): Flow<EmployeesEvent>
}