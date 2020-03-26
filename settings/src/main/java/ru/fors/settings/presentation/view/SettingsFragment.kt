package ru.fors.settings.presentation.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.preference.Preference
import androidx.preference.SwitchPreference
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.scope.currentScope
import ru.fors.navigation.ui.BasePreferenceFragment
import ru.fors.settings.R
import ru.fors.settings.presentation.viewmodel.SettingsViewModel
import ru.fors.settings.presentation.viewmodel.SettingsViewState

/**
 * Created by 23alot on 20.03.2020.
 */
class SettingsFragment : BasePreferenceFragment() {

    private val model: SettingsViewModel by currentScope.inject()

    override val layoutRes: Int
        get() = R.layout.fragment_settings

    override val shouldShowNavigationBar: Boolean
        get() = true

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preferences, "Settings")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Toolbar>(R.id.settings_toolbar).apply {
            setTitle(R.string.settings)
            setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }

        preferenceScreen.findPreference<SwitchPreference>("dark_mode_preference")
            ?.setOnPreferenceClickListener { preference ->
                model.onDarkModeChanged((preference as SwitchPreference).isChecked)
                true
            }


        preferenceScreen.findPreference<Preference>("logout_preference")
            ?.setOnPreferenceClickListener { preference ->
                model.onLogout()
                true
            }

        lifecycleScope.launch {
            model.state
                .collect { updateState(it) }
        }
        model.onDarkModeRequired()
    }

    private fun updateState(state: SettingsViewState) {
        val darkMode = preferenceScreen.findPreference<SwitchPreference>("dark_mode_preference")
        darkMode?.isChecked = state.isDarkMode
    }
}