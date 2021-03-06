package ru.fors.user.domain

import ru.fors.user.api.data.UserRepository
import ru.fors.user.api.domain.UserUseCase
import ru.fors.user.api.domain.UserUseCase.UserEvent

/**
 * Created by 23alot on 22.02.2020.
 */
class UserUseCaseImpl(
    private val userRepository: UserRepository
) : UserUseCase {
    override suspend fun invoke(): UserEvent {
        kotlin.runCatching {
                val user = userRepository.getUser()
                return UserEvent.User(user)
            }
            .onFailure {
                return UserEvent.Error(it)
            }

        return UserEvent.NotExist
    }
}