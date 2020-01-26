package ru.fors.auth.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.runBlocking
import ru.fors.auth.api.domain.SignInUseCase
import ru.fors.auth.api.domain.dto.Credentials

/**
 * Created by 23alot on 26.01.2020.
 */
class AuthViewModel(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val stateRelay = MutableLiveData<AuthPartialViewState>()

    @ExperimentalCoroutinesApi
    val state = stateRelay.asFlow()
        .scan(AuthViewState()) { state, partial -> partial(state) }
        .distinctUntilChanged()

    fun onLoginRequired(login: String?, password: String?) {
        if (!checkLogin(login) and !checkPassword(password)) {
            return
        }

        runBlocking {
            signInUseCase(Credentials(login = login!!, password = password!!))
                .runCatching {

                }
                .onFailure { stateRelay.postValue(AuthPartialViewStates.signInError(it)) }
                .onSuccess { stateRelay.postValue(AuthPartialViewStates.signInSuccess()) }
        }
    }

    private fun checkLogin(login: String?): Boolean {
        if (login.isNullOrBlank()) {
            stateRelay.postValue(AuthPartialViewStates.emptyLogin())
            return false
        }

        return true
    }

    private fun checkPassword(password: String?): Boolean {
        if (password.isNullOrBlank()) {
            stateRelay.postValue(AuthPartialViewStates.emptyLogin())
            return false
        }

        return true
    }
}