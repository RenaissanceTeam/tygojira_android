package ru.fors.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.launch
import ru.fors.router.AppRouter
import ru.fors.settings.api.domain.DarkModeUseCase

/**
 * Created by 23alot on 12.02.2020.
 */
class AppViewModel constructor(
    private val darkModeUseCase: DarkModeUseCase,
    private val router: AppRouter
) : ViewModel() {
    private val stateRelay = MutableLiveData<AppPartialViewState>()

    @ExperimentalCoroutinesApi
    val state = stateRelay.asFlow()
        .distinctUntilChanged()
        .scan(AppViewState()) { state, partial ->
            partial(state)
        }
        .flowOn(Dispatchers.Main)

    fun observeDarkModeChanges() {
        viewModelScope.launch {
            darkModeUseCase()
                .collect { event ->
                    when(event) {
                        is DarkModeUseCase.DarkModeEvent.DarkMode -> stateRelay.postValue(AppPartialViewStates.onDarkModeChanged(event.isDark))
                        else -> Unit
                    }
                }
        }
    }


    fun startAuthFlow() {
        stateRelay.postValue(AppPartialViewStates.login())
        router.onAuthStart()
    }

    fun onScreenShown(shouldShovNavigationBar: Boolean) {
        stateRelay.postValue(AppPartialViewStates.onScreenShow(shouldShovNavigationBar))
    }

    fun onUserNavigation() {
        stateRelay.postValue(AppPartialViewStates.onUserNavigation())
        router.onUser()
    }

    fun onActivitiesNavigation() {
        stateRelay.postValue(AppPartialViewStates.onActivitiesNavigation())
        router.onActivities()
    }

    fun onSettingsNavigation() {
        stateRelay.postValue(AppPartialViewStates.onSettingsNavigation())
        router.onSettings()
    }

    fun onBack() {
        router.onBack()
    }
}