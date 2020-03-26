package ru.fors.settings.di

import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.experimental.builder.scopedBy
import org.koin.experimental.builder.singleBy
import ru.fors.settings.api.data.SettingsRepository
import ru.fors.settings.api.domain.DarkModeUseCase
import ru.fors.settings.data.SharedPreferencesSettingsRepository
import ru.fors.settings.domain.DarkModeUseCaseImpl
import ru.fors.settings.presentation.view.SettingsFragment
import ru.fors.settings.presentation.viewmodel.SettingsViewModel

/**
 * Created by 23alot on 20.03.2020.
 */
@InternalCoroutinesApi
val settingsModule = module {
    scope(named<SettingsFragment>()) {
        scopedBy<DarkModeUseCase, DarkModeUseCaseImpl>()

        viewModel { SettingsViewModel(get(), get(), get()) }
    }

    singleBy<SettingsRepository, SharedPreferencesSettingsRepository>()
    singleBy<DarkModeUseCase, DarkModeUseCaseImpl>()
}