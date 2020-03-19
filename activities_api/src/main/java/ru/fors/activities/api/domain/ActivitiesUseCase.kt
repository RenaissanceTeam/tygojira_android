package ru.fors.activities.api.domain

import ru.fors.activities.api.domain.dto.Activity
import ru.fors.activities.api.domain.dto.Workload

/**
 * Created by 23alot on 23.02.2020.
 */
interface ActivitiesUseCase {
    sealed class ActivitiesEvent {
        data class Activities(val activities: List<Workload>): ActivitiesEvent()

        object NotExist : ActivitiesEvent()

        data class Error(val throwable: Throwable) : ActivitiesEvent()
    }

    suspend operator fun invoke(): ActivitiesEvent

}