package ru.fors.router

import ru.fors.Screens
import ru.fors.auth.router.AuthRouter
import ru.fors.other.router.OtherRouter
import ru.terrakok.cicerone.Router

/**
 * Created by 23alot on 04.02.2020.
 */
class AppRouter(
    private val router: Router
) : AuthRouter, OtherRouter {

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
        router.newRootScreen(Screens.Other)
    }

    override fun navigateToSettings() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigateToEmployees() {
        router.navigateTo(Screens.Employees)
    }

    fun onBack() {
        router.exit()
    }
}