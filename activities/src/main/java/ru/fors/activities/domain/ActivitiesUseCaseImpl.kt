package ru.fors.activities.domain

import ru.fors.activities.api.data.ActivitiesRepository
import ru.fors.activities.api.domain.ActivitiesUseCase
import ru.fors.activities.api.domain.ActivitiesUseCase.ActivitiesEvent
import ru.fors.user.api.data.UserRepository

/**
 * Created by 23alot on 23.02.2020.
 */
class ActivitiesUseCaseImpl(
    private val activitiesRepository: ActivitiesRepository,
    private val userRepository: UserRepository
): ActivitiesUseCase {

    override suspend fun invoke(): ActivitiesEvent {
        runCatching {
            val userId = userRepository.getUser().id
            val activities = activitiesRepository.getActivities(userId)
            if (activities.isEmpty()) return ActivitiesEvent.NotExist

            return ActivitiesEvent.Activities(activities)
        }
            .onFailure {
                return ActivitiesEvent.Error(it)
            }

        return ActivitiesEvent.NotExist
    }
}