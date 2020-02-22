package ru.fors.user.api.domain

import ru.fors.user.api.domain.dto.Employee

/**
 * Created by 23alot on 22.02.2020.
 */
interface UserUseCase {
    sealed class UserEvent {
        data class User(val user: Employee): UserEvent()

        object NotExist : UserEvent()

        data class Error(val throwable: Throwable) : UserEvent()
    }

    suspend operator fun invoke(): UserEvent
}