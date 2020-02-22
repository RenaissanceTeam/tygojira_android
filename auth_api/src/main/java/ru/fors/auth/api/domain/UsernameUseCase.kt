package ru.fors.auth.api.domain

/**
 * Created by 23alot on 22.02.2020.
 */
interface UsernameUseCase {

    sealed class UsernameEvent {
        data class Username(val username: String): UsernameEvent()

        object NotExist : UsernameEvent()

        data class Error(val throwable: Throwable) : UsernameEvent()
    }

    suspend operator fun invoke(): UsernameEvent

}