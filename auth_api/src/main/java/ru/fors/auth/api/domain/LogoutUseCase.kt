package ru.fors.auth.api.domain

/**
 * Created by 23alot on 20.03.2020.
 */
interface LogoutUseCase {
    sealed class LogoutEvent {
        object Success : LogoutEvent()

        data class Error(val throwable: Throwable): LogoutEvent()
    }

    suspend operator fun invoke(): LogoutEvent
}