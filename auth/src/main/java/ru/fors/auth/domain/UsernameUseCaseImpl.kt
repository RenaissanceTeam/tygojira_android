package ru.fors.auth.domain

import ru.fors.auth.api.data.UsernameRepository
import ru.fors.auth.api.domain.UsernameUseCase
import ru.fors.auth.api.domain.UsernameUseCase.UsernameEvent

/**
 * Created by 23alot on 22.02.2020.
 */
class UsernameUseCaseImpl(
    private val usernameRepository: UsernameRepository
) : UsernameUseCase {
    override suspend fun invoke(): UsernameEvent {
        runCatching {
            val username = usernameRepository.requireUsername()
            if (username.isBlank()) return UsernameEvent.NotExist
            return UsernameEvent.Username(username)
        }
            .onFailure { return UsernameEvent.Error(it) }

        return UsernameEvent.NotExist
    }
}