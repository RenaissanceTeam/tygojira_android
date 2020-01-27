package ru.fors.auth.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.scan
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
        .flowOn(Dispatchers.Main)

    fun onLoginRequired(login: String?, password: String?) {
        if (!checkLogin(login) and !checkPassword(password)) {
            return
        }

        viewModelScope.launch {
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