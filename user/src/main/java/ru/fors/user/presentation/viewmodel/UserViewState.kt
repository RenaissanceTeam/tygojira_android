package ru.fors.user.presentation.viewmodel

import ru.fors.user.api.domain.dto.Employee

/**
 * Created by 23alot on 22.02.2020.
 */
data class UserViewState(
    val user: Employee? = null
)