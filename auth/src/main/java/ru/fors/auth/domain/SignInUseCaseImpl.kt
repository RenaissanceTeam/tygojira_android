package ru.fors.auth.domain

import ru.fors.auth.api.data.TokenRepository
import ru.fors.auth.api.domain.SignInUseCase
import ru.fors.auth.api.domain.dto.Credentials
import ru.fors.auth.api.data.AuthRepository
import ru.fors.auth.api.data.UsernameRepository

/**
 * Created by 23alot on 19.01.2020.
 */
class SignInUseCaseImpl(
    private val tokenRepository: TokenRepository,
    private val authRepository: AuthRepository,
    private val usernameRepository: UsernameRepository
) : SignInUseCase {

    override suspend fun invoke(credentials: Credentials): SignInUseCase.TokenEvent {
        runCatching {
            val token = authRepository.login(credentials).token
            if (token.isBlank()) return SignInUseCase.TokenEvent.NotExist
            tokenRepository.setToken(token)
            usernameRepository.setUsername(credentials.login)
            return SignInUseCase.TokenEvent.Token(token)
        }
            .onFailure {
                return SignInUseCase.TokenEvent.Error(it)
            }

        return SignInUseCase.TokenEvent.NotExist
    }

}