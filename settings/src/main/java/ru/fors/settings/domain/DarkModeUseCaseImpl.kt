package ru.fors.settings.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.fors.settings.api.data.SettingsRepository
import ru.fors.settings.api.domain.DarkModeUseCase

/**
 * Created by 23alot on 22.02.2020.
 */
class DarkModeUseCaseImpl constructor(private val repository: SettingsRepository): DarkModeUseCase {

    override suspend fun invoke(): Flow<DarkModeUseCase.DarkModeEvent> = repository.observeDarkModeChanges().map {
        DarkModeUseCase.DarkModeEvent.DarkMode(it)
    }

    override suspend fun invoke(isDark: Boolean): DarkModeUseCase.DarkModeEvent {
        repository.setDarkMode(isDark)
        return DarkModeUseCase.DarkModeEvent.NotExist
    }
}