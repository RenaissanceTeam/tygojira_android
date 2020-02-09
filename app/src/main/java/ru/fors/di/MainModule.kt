package ru.fors.di

import org.koin.dsl.module
import ru.fors.auth.presentation.view.fragment.AuthFragment
import ru.fors.auth.router.AuthRouter
import ru.fors.router.AppRouter

/**
 * Created by 23alot on 04.02.2020.
 */
val routerModule = module {
    single<AuthRouter> { AppRouter(get()) }
}