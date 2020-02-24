package ru.fors.activities.api.domain

import ru.fors.activities.api.domain.dto.Activity

/**
 * Created by 23alot on 23.02.2020.
 */
interface ActivitiesUseCase {
    sealed class ActivitiesEvent {
        data class Activities(val activities: List<Activity>): ActivitiesEvent()

        object NotExist : ActivitiesEvent()

        data class Error(val throwable: Throwable) : ActivitiesEvent()
    }

    suspend operator fun invoke(): ActivitiesEvent

}