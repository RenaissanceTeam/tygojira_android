package router

import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen

/**
 * Created by 23alot on 02.02.2020.
 * by Konstantin Tskhovrebov (aka @terrakok)
 * @link https://gitlab.com/terrakok/gitlab-client/blob/develop/app/src/main/java/ru/terrakok/gitlabclient/model/system/flow/FlowRouter.kt
 */
class FlowRouter(private val appRouter: Router) : Router() {

    fun startFlow(screen: SupportAppScreen) {
        appRouter.navigateTo(screen)
    }

    fun newRootFlow(screen: SupportAppScreen) {
        appRouter.newRootScreen(screen)
    }

    fun finishFlow() {
        appRouter.exit()
    }
}
