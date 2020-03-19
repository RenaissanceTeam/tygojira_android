package ru.fors.presentation.viewmodel

/**
 * Created by 23alot on 12.02.2020.
 */
data class AppViewState(
    val shouldShowBottomNavigation: Boolean = false,
    val bottomState: BottomState = BottomState.User
)

enum class BottomState {
    User, Activities, Settings
}