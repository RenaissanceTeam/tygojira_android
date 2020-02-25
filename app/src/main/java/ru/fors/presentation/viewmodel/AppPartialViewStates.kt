package ru.fors.presentation.viewmodel

/**
 * Created by 23alot on 12.02.2020.
 */
typealias AppPartialViewState = (AppViewState) -> AppViewState

object AppPartialViewStates {

    fun login(): AppPartialViewState = { previousViewState ->
        previousViewState.copy(
            shouldShowBottomNavigation = false
        )
    }

    fun onScreenShow(shouldShowNavigationBar: Boolean): AppPartialViewState = { previousViewState ->
        previousViewState.copy(
            shouldShowBottomNavigation = shouldShowNavigationBar
        )
    }

    fun onUserNavigation(): AppPartialViewState = { previousViewState ->
        previousViewState.copy(
            shouldShowBottomNavigation = true,
            bottomState = BottomState.User
        )
    }

    fun onActivitiesNavigation(): AppPartialViewState = { previousViewState ->
        previousViewState.copy(
            shouldShowBottomNavigation = true,
            bottomState = BottomState.Activities
        )
    }

    fun onSettingsNavigation(): AppPartialViewState = { previousViewState ->
        previousViewState.copy(
            shouldShowBottomNavigation = true,
            bottomState = BottomState.Settings
        )
    }
}