package ru.fors.auth.presentation.viewmodel

/**
 * Created by 23alot on 26.01.2020.
 */

typealias AuthPartialViewState = (AuthViewState) -> AuthViewState

object AuthPartialViewStates {

    fun start(): AuthPartialViewState = { previousViewState ->
        previousViewState.copy(
            event = AuthEvent.Start,
            loginState = LoginState.Default,
            passwordState = PasswordState.Default
        )
    }

    fun emptyLogin(): AuthPartialViewState = { previousViewState ->
        previousViewState.copy(
            event = AuthEvent.LoginError,
            loginState = LoginState.Empty
        )
    }

    fun shortLogin(): AuthPartialViewState = { previousViewState ->
        previousViewState.copy(
            event = AuthEvent.LoginError,
            loginState = LoginState.Short
        )
    }

    fun notAllowedLogin(): AuthPartialViewState = { previousViewState ->
        previousViewState.copy(
            event = AuthEvent.LoginError,
            loginState = LoginState.NotAllowed
        )
    }

    fun emptyPassword(): AuthPartialViewState = { previousViewState ->
        previousViewState.copy(
            event = AuthEvent.PasswordError,
            passwordState = PasswordState.Empty
        )
    }

    fun weakPassword(): AuthPartialViewState = { previousViewState ->
        previousViewState.copy(
            event = AuthEvent.PasswordError,
            passwordState = PasswordState.Weak
        )
    }

    fun notAllowedPassword(): AuthPartialViewState = { previousViewState ->
        previousViewState.copy(
            event = AuthEvent.PasswordError,
            passwordState = PasswordState.NotAllowed
        )
    }

    fun shortPassword(): AuthPartialViewState = { previousViewState ->
        previousViewState.copy(
            event = AuthEvent.PasswordError,
            passwordState = PasswordState.Short
        )
    }

    fun signInError(throwable: Throwable): AuthPartialViewState = { previousViewState ->
        previousViewState.copy(
            event = AuthEvent.SignInError,
            signInState = SignInState.Error(throwable)
        )
    }

    fun signInSuccess(): AuthPartialViewState = { previousViewState ->
        previousViewState.copy(
            event = AuthEvent.SignInSuccessful
        )
    }
}
