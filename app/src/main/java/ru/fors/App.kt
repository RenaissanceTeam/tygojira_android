package ru.fors

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import di.navigationModule
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.fors.activities.di.activitiesModule
import ru.fors.auth.di.authModule
import ru.fors.di.routerModule
import ru.fors.employees.di.employeesModule
import ru.fors.network.di.networkModule
import ru.fors.other.di.otherModule
import ru.fors.settings.di.settingsModule
import ru.fors.user.di.userModule

/**
 * Created by 23alot on 26.01.2020.
 */
class App : Application() {
    @InternalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        startKoin {
            androidContext(this@App.applicationContext)
            modules(
                listOf(
                    navigationModule,
                    authModule,
                    networkModule,
                    userModule,
                    activitiesModule,
                    otherModule,
                    settingsModule,
                    employeesModule,
                    routerModule
                )
            )
        }
    }
}