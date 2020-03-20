package ru.fors.settings.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import ru.fors.settings.api.data.SettingsRepository

/**
 * Created by 23alot on 19.03.2020.
 */
class SharedPreferencesSettingsRepository(private val context: Context) : SettingsRepository {
    companion object {
        private const val NAME = "SharedPreferencesSettingsRepository"
        private const val IS_DARK_MODE = "IS_DARK_MODE"
    }

    private val preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)

    private val isDarkMode = MutableLiveData<Boolean>()

    @ExperimentalCoroutinesApi
    private val state = isDarkMode.asFlow()
        .onStart {
            val startValue = preferences.getBoolean(IS_DARK_MODE, true)
            this.emit(startValue)
        }
        .distinctUntilChanged()
        .flowOn(Dispatchers.Main)


    override suspend fun setDarkMode(isDarkMode: Boolean) {
        preferences.edit().putBoolean(IS_DARK_MODE, isDarkMode).apply()
        this.isDarkMode.value = isDarkMode
    }

    override suspend fun observeDarkModeChanges(): Flow<Boolean> = state
}