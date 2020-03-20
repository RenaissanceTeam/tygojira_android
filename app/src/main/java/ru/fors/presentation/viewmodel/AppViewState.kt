package ru.fors.presentation.viewmodel

/**
 * Created by 23alot on 12.02.2020.
 */
data class AppViewState(
    val shouldShowBottomNavigation: Boolean = false,
    val bottomState: BottomState = BottomState.User,
    val theme: Theme = Theme.DEFAULT
)

enum class BottomState {
    User, Activities, Settings
}

enum class Theme {
    DARK, LIGHT, DEFAULT
}