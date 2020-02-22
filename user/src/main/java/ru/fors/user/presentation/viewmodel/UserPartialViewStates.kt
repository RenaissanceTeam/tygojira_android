package ru.fors.user.presentation.viewmodel

import ru.fors.user.api.domain.dto.Employee

/**
 * Created by 23alot on 22.02.2020.
 */
typealias UserPartialViewState = (UserViewState) -> UserViewState

object UserPartialViewStates {

    fun onStart(): UserPartialViewState = { previousViewState ->
        previousViewState.copy(
            user = null
        )
    }

    fun onUserNotExist(): UserPartialViewState = { previousViewState ->
        previousViewState.copy(
            user = null
        )
    }

    fun onUserLoaded(user: Employee): UserPartialViewState = { previousViewState ->
        previousViewState.copy(
            user = user
        )
    }
}