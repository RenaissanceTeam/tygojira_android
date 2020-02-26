package ru.fors.user.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.launch
import ru.fors.user.api.domain.UserUseCase

/**
 * Created by 23alot on 26.01.2020.
 */
class UserViewModel(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val stateRelay = MutableLiveData<UserPartialViewState>()

    @ExperimentalCoroutinesApi
    val state = stateRelay.asFlow()
        .scan(UserViewState()) { state, partial -> partial(state) }
        .distinctUntilChanged()
        .flowOn(Dispatchers.Main)

    fun onUserRequired() {
        viewModelScope.launch {
            val userEvent = userUseCase()
            when(userEvent) {
                is UserUseCase.UserEvent.NotExist -> stateRelay.postValue(UserPartialViewStates.onUserNotExist())
                is UserUseCase.UserEvent.User -> stateRelay.postValue(UserPartialViewStates.onUserLoaded(userEvent.user))
                is UserUseCase.UserEvent.Error -> stateRelay.postValue(UserPartialViewStates.onUserNotExist())
            }
        }

    }
}