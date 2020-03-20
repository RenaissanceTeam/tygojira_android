package ru.fors.settings.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.launch
import ru.fors.auth.api.domain.LogoutUseCase
import ru.fors.settings.api.domain.DarkModeUseCase
import ru.fors.settings.router.SettingsRouter

/**
 * Created by 23alot on 20.03.2020.
 */
class SettingsViewModel(
    private val darkModeUseCase: DarkModeUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val router: SettingsRouter
) : ViewModel() {

    private val stateRelay = MutableLiveData<SettingsPartialViewState>()

    @ExperimentalCoroutinesApi
    val state = stateRelay.asFlow()
        .scan(SettingsViewState(isDarkMode = true)) { state, partial -> partial(state) }
        .distinctUntilChanged()
        .flowOn(Dispatchers.Main)

    @UseExperimental(InternalCoroutinesApi::class)
    fun onDarkModeRequired() {
        viewModelScope.launch {
            darkModeUseCase()
                .collect { event ->
                    when(event) {
                        is DarkModeUseCase.DarkModeEvent.DarkMode -> stateRelay.postValue(SettingsPartialViewStates.onDarkModeChanged(event.isDark))
                    }
                }
        }
    }

    fun onDarkModeChanged(isDarkMode: Boolean) {
        viewModelScope.launch {
            val event = darkModeUseCase(isDarkMode)
        }
    }

    fun onLogout() {
        viewModelScope.launch {
            logoutUseCase()
            router.onLogout()
        }
    }
}