package ru.fors.activities.api.data

import ru.fors.activities.api.domain.dto.Activity
import ru.fors.activities.api.domain.dto.Workload

/**
 * Created by 23alot on 23.02.2020.
 */
interface ActivitiesRepository {

    suspend fun getActivities(id: Long): List<Workload>

}