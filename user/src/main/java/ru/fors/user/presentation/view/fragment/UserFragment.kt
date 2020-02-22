package ru.fors.user.presentation.view.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.scope.currentScope
import ru.fors.navigation.ui.BaseFragment
import ru.fors.user.R
import ru.fors.user.presentation.viewmodel.UserViewModel
import ru.fors.user.presentation.viewmodel.UserViewState

/**
 * Created by 23alot on 26.01.2020.
 */
@InternalCoroutinesApi
class UserFragment : BaseFragment() {

    private val model: UserViewModel by currentScope.inject()

    override val layoutRes: Int
        get() = R.layout.fragment_user

    override val shouldShowNavigationBar: Boolean
        get() = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            model.state
                .collect { updateState(it) }
        }
        model.onUserRequired()
    }

    private fun updateState(state: UserViewState) {
        state.user?.let { employee ->
            user_first_name.text = employee.firstName
            user_middle_name.text = employee.middleName
            user_last_name.text = employee.lastName
            user_position.text = employee.position
            user_skills.text = employee.skills.joinToString()
            user_subdivision.text = employee.subdivision
        }
    }
}