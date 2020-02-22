package ru.fors

import android.app.Application
import di.navigationModule
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.core.context.startKoin
import ru.fors.auth.di.authModule
import ru.fors.di.routerModule
import ru.fors.network.di.networkModule
import ru.fors.user.di.userModule
import ru.terrakok.cicerone.Cicerone

/**
 * Created by 23alot on 26.01.2020.
 */
class App : Application() {
    @InternalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    navigationModule,
                    authModule,
                    networkModule,
                    userModule,
                    routerModule
                )
            )
        }
    }
}