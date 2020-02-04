package ru.fors.router

import router.FlowRouter
import ru.fors.Screens
import ru.fors.auth.router.AuthRouter

/**
 * Created by 23alot on 04.02.2020.
 */
class Router(
    private val flowRouter: FlowRouter
): AuthRouter {
    override fun onAuthSuccess() {
        flowRouter.newRootFlow(Screens.Main)
    }
}