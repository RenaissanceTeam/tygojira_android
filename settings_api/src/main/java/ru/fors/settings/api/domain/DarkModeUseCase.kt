package ru.fors.settings.api.domain

import kotlinx.coroutines.flow.Flow

/**
 * Created by 23alot on 22.02.2020.
 */
interface DarkModeUseCase {
    sealed class DarkModeEvent {
        data class DarkMode(val isDark: Boolean): DarkModeEvent()

        object NotExist : DarkModeEvent()

        data class Error(val throwable: Throwable) : DarkModeEvent()
    }

    suspend operator fun invoke(): Flow<DarkModeEvent>

    suspend operator fun invoke(isDark: Boolean): DarkModeEvent
}