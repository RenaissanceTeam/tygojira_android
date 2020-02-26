package ru.fors.employees.api.data

import kotlinx.coroutines.flow.Flow
import ru.fors.user.api.domain.dto.Employee

/**
 * Created by 23alot on 26.02.2020.
 */
interface EmployeesRepository {

    suspend fun getEmployees(): Flow<List<Employee>>
}