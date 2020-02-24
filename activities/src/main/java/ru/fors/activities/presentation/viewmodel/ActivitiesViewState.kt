package ru.fors.activities.presentation.viewmodel

import org.threeten.bp.LocalDate
import ru.fors.activities.api.domain.dto.Activity

/**
 * Created by 23alot on 23.02.2020.
 */
data class ActivitiesViewState(
    val activities: List<Activity> = listOf(),
    val date: LocalDate? = null
)