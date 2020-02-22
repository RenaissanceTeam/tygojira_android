package ru.fors.user.data.framework

import ru.fors.user.api.domain.dto.Employee

/**
 * Created by 23alot on 22.02.2020.
 */
data class UserResponse(
    val id: Long,
    val roles: List<String>,
    val employee: Employee
)