package ru.fors.user.api.data

import ru.fors.user.api.domain.dto.Employee

/**
 * Created by 23alot on 22.02.2020.
 */
interface UserRepository {

    suspend fun getUser(): Employee
}