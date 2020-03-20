package ru.fors.router

import ru.fors.Screens
import ru.fors.auth.router.AuthRouter
import ru.fors.other.router.OtherRouter
import ru.fors.settings.router.SettingsRouter
import ru.terrakok.cicerone.Router

/**
 * Created by 23alot on 04.02.2020.
 */
class AppRouter(
    private val router: Router
) : AuthRouter, OtherRouter, SettingsRouter {

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
        router.newRootScreen(Screens.User)
    }

    fun onSettings() {
        router.newRootScreen(Screens.Other)
    }

    override fun navigateToSettings() {
        router.navigateTo(Screens.Settings)
    }

    override fun navigateToEmployees() {
        router.navigateTo(Screens.Employees)
    }

    override fun onLogout() {
        router.newRootScreen(Screens.Auth)
    }

    fun onBack() {
        router.exit()
    }
}