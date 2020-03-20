package ru.fors.settings.presentation.viewmodel

/**
 * Created by 23alot on 20.03.2020.
 */
typealias SettingsPartialViewState = (SettingsViewState) -> SettingsViewState

object SettingsPartialViewStates {

    fun onDarkModeChanged(isDarkMode: Boolean): SettingsPartialViewState = { previousViewState ->
        previousViewState.copy(
            isDarkMode = isDarkMode
        )
    }
}