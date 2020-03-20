package ru.fors.presentation.viewmodel

/**
 * Created by 23alot on 12.02.2020.
 */
typealias AppPartialViewState = (AppViewState) -> AppViewState

object AppPartialViewStates {

    fun login(): AppPartialViewState = { previousViewState ->
        previousViewState.copy(
            shouldShowBottomNavigation = false,
            theme = Theme.DEFAULT
        )
    }

    fun onScreenShow(shouldShowNavigationBar: Boolean): AppPartialViewState = { previousViewState ->
        previousViewState.copy(
            shouldShowBottomNavigation = shouldShowNavigationBar,
            theme = Theme.DEFAULT
        )
    }

    fun onUserNavigation(): AppPartialViewState = { previousViewState ->
        previousViewState.copy(
            shouldShowBottomNavigation = true,
            bottomState = BottomState.User,
            theme = Theme.DEFAULT
        )
    }

    fun onActivitiesNavigation(): AppPartialViewState = { previousViewState ->
        previousViewState.copy(
            shouldShowBottomNavigation = true,
            bottomState = BottomState.Activities,
            theme = Theme.DEFAULT
        )
    }

    fun onSettingsNavigation(): AppPartialViewState = { previousViewState ->
        previousViewState.copy(
            shouldShowBottomNavigation = true,
            bottomState = BottomState.Settings,
            theme = Theme.DEFAULT
        )
    }

    fun onDarkModeChanged(isDarkMode: Boolean): AppPartialViewState = { previousViewState ->
        previousViewState.copy(
            theme = if (isDarkMode) Theme.DARK else Theme.LIGHT
        )
    }
}