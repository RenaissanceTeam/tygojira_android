package ru.fors.other.presentation.viewmodel

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
import ru.fors.other.router.OtherRouter

/**
 * Created by 23alot on 24.02.2020.
 */
class OtherViewModel(
    private val router: OtherRouter
) : ViewModel() {

    fun onSettingsClicked() {
        router.navigateToSettings()
    }

    fun onEmployeesClick() {
        router.navigateToEmployees()
    }
}