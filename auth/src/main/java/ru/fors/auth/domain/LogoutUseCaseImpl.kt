package ru.fors.auth.domain

import ru.fors.auth.api.data.TokenRepository
import ru.fors.auth.api.domain.LogoutUseCase

/**
 * Created by 23alot on 20.03.2020.
 */
class LogoutUseCaseImpl constructor(
    private val tokenRepository: TokenRepository
): LogoutUseCase {

    override suspend fun invoke(): LogoutUseCase.LogoutEvent {
        runCatching {
            tokenRepository.discard()

            return LogoutUseCase.LogoutEvent.Success
        }
            .onFailure {
                return LogoutUseCase.LogoutEvent.Error(it)
            }

        return LogoutUseCase.LogoutEvent.Success
    }
}