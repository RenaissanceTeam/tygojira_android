package ru.fors.auth.domain

import ru.fors.auth.api.data.TokenRepository
import ru.fors.auth.api.domain.SignInUseCase
import ru.fors.auth.api.domain.dto.Credentials
import ru.fors.auth.data.repository.auth.AuthRepository

/**
 * Created by 23alot on 19.01.2020.
 */
class SignInUseCaseImpl(
    private val tokenRepository: TokenRepository,
    private val authRepository: AuthRepository
) : SignInUseCase {

    override suspend fun invoke(credentials: Credentials): Boolean {
        val token = authRepository.login(credentials)
        tokenRepository.setToken(token.token)
        return true
    }

}