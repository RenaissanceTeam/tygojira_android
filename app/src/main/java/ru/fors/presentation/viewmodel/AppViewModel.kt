package ru.fors.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.scan
import ru.fors.router.AppRouter

/**
 * Created by 23alot on 12.02.2020.
 */
class AppViewModel constructor(
    private val router: AppRouter
) : ViewModel() {
    private val stateRelay = MutableLiveData<AppPartialViewState>()

    @ExperimentalCoroutinesApi
    val state = stateRelay.asFlow()
        .scan(AppViewState()) { state, partial -> partial(state) }
        .distinctUntilChanged()
        .flowOn(Dispatchers.IO)


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
}