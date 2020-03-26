package ru.fors.settings.api.data

import kotlinx.coroutines.flow.Flow

/**
 * Created by 23alot on 22.02.2020.
 */
interface SettingsRepository {

    suspend fun setDarkMode(isDarkMode: Boolean)

    suspend fun observeDarkModeChanges(): Flow<Boolean>
}