package ru.fors.activities.presentation.viewmodel

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
import ru.fors.activities.api.domain.ActivitiesUseCase

/**
 * Created by 23alot on 23.02.2020.
 */
class ActivitiesViewModel(
    private val activitiesUseCase: ActivitiesUseCase
) : ViewModel() {

    private val stateRelay = MutableLiveData<ActivitiesPartialViewState>()

    @ExperimentalCoroutinesApi
    val state = stateRelay.asFlow()
        .scan(ActivitiesViewState()) { state, partial -> partial(state) }
        .distinctUntilChanged()
        .flowOn(Dispatchers.Main)

    fun onActivitiesRequired() {
        viewModelScope.launch {
            val activitiesEvent = activitiesUseCase.invoke()
            when(activitiesEvent) {
                is ActivitiesUseCase.ActivitiesEvent.NotExist -> stateRelay.postValue(UserPartialViewStates.onActivitiesNotExist())
                is ActivitiesUseCase.ActivitiesEvent.Activities -> stateRelay.postValue(UserPartialViewStates.onActivitiesLoaded(activitiesEvent.activities))
                is ActivitiesUseCase.ActivitiesEvent.Error -> stateRelay.postValue(UserPartialViewStates.onActivitiesNotExist())
            }
        }

    }
}