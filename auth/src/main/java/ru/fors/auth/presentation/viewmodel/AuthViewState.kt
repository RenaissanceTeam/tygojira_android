package ru.fors.auth.presentation.viewmodel

/**
 * Created by 23alot on 26.01.2020.
 */
data class AuthViewState(
    val event: AuthEvent = AuthEvent.SignInSuccessful,
    val loginState: LoginState = LoginState.Default,
    val passwordState: PasswordState = PasswordState.Default,
    val signInState: SignInState = SignInState.Default
)

sealed class AuthEvent {
    object Start : AuthEvent()

    object SignInSuccessful : AuthEvent()

    object SignInError : AuthEvent()

    object LoginError : AuthEvent()

    object PasswordError : AuthEvent()
}

sealed class LoginState {
    object Default : LoginState()

    object Empty : LoginState()

    object Short : LoginState()

    object NotAllowed : LoginState()
}

sealed class PasswordState {
    object Default : PasswordState()

    object Empty : PasswordState()

    object Weak : PasswordState()

    object Short : PasswordState()

    object NotAllowed : PasswordState()
}

sealed class SignInState {
    object Default : SignInState()

    data class Error(val throwable: Throwable): SignInState()
}
