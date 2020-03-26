package ru.fors.settings.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import ru.fors.settings.api.data.SettingsRepository
import ru.fors.settings.api.domain.DarkModeUseCase
import ru.fors.settings.api.domain.DarkModeUseCase.DarkModeEvent

/**
 * Created by 23alot on 22.02.2020.
 */
class DarkModeUseCaseImpl constructor(private val repository: SettingsRepository) :
    DarkModeUseCase {

    override suspend fun invoke(): Flow<DarkModeEvent> =
        repository.observeDarkModeChanges()
            .map {
                DarkModeEvent.DarkMode(it)
            }
            .catch<DarkModeEvent> { error ->
                emit(DarkModeEvent.Error(error))
            }

    override suspend fun invoke(isDark: Boolean): DarkModeEvent {
        repository.setDarkMode(isDark)
        return DarkModeEvent.NotExist
    }
}