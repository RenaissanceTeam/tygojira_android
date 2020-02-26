package ru.fors.employees.presentation.view.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.scope.currentScope
import ru.fors.employees.R
import ru.fors.employees.presentation.viewmodel.EmployeesViewModel
import ru.fors.employees.presentation.viewmodel.EmployeesViewState
import ru.fors.navigation.ui.BaseFragment

/**
 * Created by 23alot on 26.01.2020.
 */
@InternalCoroutinesApi
class EmployeesFragment : BaseFragment() {

    private val model: EmployeesViewModel by currentScope.inject()

    override val layoutRes: Int
        get() = R.layout.fragment_employees

    override val shouldShowNavigationBar: Boolean
        get() = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            model.state
                .collect { updateState(it) }
        }
        model.onEmployeesRequired()
    }

    private fun updateState(state: EmployeesViewState) {
        val a = 0
    }
}