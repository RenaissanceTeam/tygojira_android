package ru.fors

import androidx.fragment.app.Fragment
import kotlinx.coroutines.InternalCoroutinesApi
import ru.fors.activities.presentation.view.fragment.ActivitiesFragment
import ru.fors.auth.presentation.view.fragment.AuthFragment
import ru.fors.employees.presentation.view.fragment.EmployeesFragment
import ru.fors.other.presentation.view.fragment.OtherFragment
import ru.fors.user.presentation.view.fragment.UserFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

/**
 * Created by 23alot on 04.02.2020.
 */

object Screens {

    object Auth: SupportAppScreen() {
        @InternalCoroutinesApi
        override fun getFragment(): Fragment = AuthFragment()
    }

    object Main: SupportAppScreen() {
        @InternalCoroutinesApi
        override fun getFragment(): Fragment = UserFragment()
    }

    object Activities: SupportAppScreen() {
        @InternalCoroutinesApi
        override fun getFragment(): Fragment = ActivitiesFragment()
    }

    object Other: SupportAppScreen() {
        @InternalCoroutinesApi
        override fun getFragment(): Fragment = OtherFragment()
    }

    object Employees: SupportAppScreen() {
        @InternalCoroutinesApi
        override fun getFragment(): Fragment = EmployeesFragment()
    }
}