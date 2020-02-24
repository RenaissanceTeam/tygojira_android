package ru.fors.activities.presentation.viewmodel

import ru.fors.activities.api.domain.dto.Activity

/**
 * Created by 23alot on 23.02.2020.
 */
typealias ActivitiesPartialViewState = (ActivitiesViewState) -> ActivitiesViewState

object UserPartialViewStates {

    fun onStart(): ActivitiesPartialViewState = { previousViewState ->
        previousViewState.copy()
    }

    fun onActivitiesNotExist(): ActivitiesPartialViewState = { previousViewState ->
        previousViewState.copy()
    }

    fun onActivitiesLoaded(activities: List<Activity>): ActivitiesPartialViewState = { previousViewState ->
        previousViewState.copy(
            activities = activities
        )
    }
}