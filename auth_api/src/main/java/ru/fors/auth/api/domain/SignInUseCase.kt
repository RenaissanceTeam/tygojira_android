package ru.fors.auth.api.domain

import ru.fors.auth.api.domain.dto.Credentials
import ru.fors.auth.api.domain.dto.TokenResponse

/**
 * Created by 23alot on 18.01.2020.
 */
interface SignInUseCase {

    sealed class TokenEvent {
        object NotExist : TokenEvent()

        data class Token(val token: String) : TokenEvent()

        data class Error(val throwable: Throwable): TokenEvent()
    }

    suspend operator fun invoke(credentials: Credentials): TokenEvent
    
}