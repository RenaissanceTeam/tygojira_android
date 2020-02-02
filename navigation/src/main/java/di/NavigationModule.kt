package di

import org.koin.core.module.Module
import org.koin.dsl.module
import router.FlowRouter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

/**
 * Created by 23alot on 02.02.2020.
 */

fun navigationModule(globalRouter: Router): Module {
    val cicerone = Cicerone.create(FlowRouter(globalRouter))
    return module {
        single<FlowRouter> { cicerone.router }
        single<NavigatorHolder> { cicerone.navigatorHolder }
    }
}