package ru.fors.employees.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.fors.employees.api.domain.EmployeesUseCase
import ru.fors.employees.api.domain.EmployeesUseCase.EmployeesEvent
import ru.fors.user.api.domain.UserUseCase

/**
 * Created by 23alot on 26.02.2020.
 */
class EmployeesViewModel(
    private val employeesUseCase: EmployeesUseCase
) : ViewModel() {

    private val stateRelay = MutableLiveData<EmployeesPartialViewState>()

    @ExperimentalCoroutinesApi
    val state = stateRelay.asFlow()
        .debounce(200L)
        .scan(EmployeesViewState()) { state, partial -> partial(state) }
        .distinctUntilChanged()
        .flowOn(Dispatchers.Main)

    fun onEmployeesRequired() {
        viewModelScope.launch {
            employeesUseCase()
                .collect { event ->
                    when(event) {
                        is EmployeesEvent.Employees -> stateRelay.postValue(EmployeesPartialViewStates.onEmployeesLoaded(event.employees))
                    }
                }
        }

    }

    fun onEmployeeSelected(position: Int) {
        stateRelay.postValue(EmployeesPartialViewStates.onEmployeeSelected(position))
    }

    fun onResetSelection() {
        stateRelay.postValue(EmployeesPartialViewStates.onResetSelectedEmployee())
    }

    fun onQueryTextChanged(text: String) {
        stateRelay.postValue(EmployeesPartialViewStates.onQueryChanged(text))
    }
}