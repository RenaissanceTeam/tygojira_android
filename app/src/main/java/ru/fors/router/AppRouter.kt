package ru.fors.router

import ru.fors.Screens
import ru.fors.auth.router.AuthRouter
import ru.terrakok.cicerone.Router

/**
 * Created by 23alot on 04.02.2020.
 */
class AppRouter(
    private val router: Router
) : AuthRouter {

    fun onAuthStart() {
        router.newRootScreen(Screens.Auth)
    }

    override fun onAuthSuccess() {
        router.newRootScreen(Screens.Main)
    }

    fun onActivities() {
        router.newRootScreen(Screens.Activities)
    }

    fun onUser() {

    }

    fun onSettings() {

    }
}