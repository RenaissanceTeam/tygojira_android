package ru.fors

import android.app.Application
import org.koin.core.context.startKoin
import ru.fors.auth.di.authModule
import ru.fors.network.di.networkModule

/**
 * Created by 23alot on 26.01.2020.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(authModule, networkModule))
        }
    }
}