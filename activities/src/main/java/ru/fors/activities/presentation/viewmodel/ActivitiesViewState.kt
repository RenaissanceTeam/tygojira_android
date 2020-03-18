package ru.fors.activities.presentation.viewmodel

import org.threeten.bp.LocalDate
import ru.fors.activities.api.domain.dto.Activity
import ru.fors.activities.api.domain.dto.Workload

/**
 * Created by 23alot on 23.02.2020.
 */
data class ActivitiesViewState(
    val activities: List<Workload> = listOf(),
    val date: LocalDate? = null
)