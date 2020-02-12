package ru.fors

import androidx.fragment.app.Fragment
import kotlinx.coroutines.InternalCoroutinesApi
import ru.fors.auth.presentation.view.fragment.AuthFragment
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
        //TODO: сделать Main экран
//        override fun getFragment(): Fragment {
//
//        }
    }
}