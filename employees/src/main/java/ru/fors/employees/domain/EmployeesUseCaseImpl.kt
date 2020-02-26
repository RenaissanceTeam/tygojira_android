package ru.fors.employees.domain

import kotlinx.coroutines.flow.*
import ru.fors.employees.api.data.EmployeesRepository
import ru.fors.employees.api.domain.EmployeesUseCase
import ru.fors.employees.api.domain.EmployeesUseCase.EmployeesEvent

/**
 * Created by 23alot on 26.02.2020.
 */
class EmployeesUseCaseImpl(
    private val employeesRepository: EmployeesRepository
): EmployeesUseCase {

    override suspend fun invoke(): Flow<EmployeesEvent> = employeesRepository
        .getEmployees()
        .mapNotNull { EmployeesEvent.Employees(it) }
        .catch<EmployeesEvent> { error ->
            emit(EmployeesEvent.Error(error))
        }
}