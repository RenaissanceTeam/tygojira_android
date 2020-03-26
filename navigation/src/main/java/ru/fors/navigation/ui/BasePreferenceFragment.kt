package ru.fors.navigation.ui

import android.os.Bundle
import android.os.Handler
import androidx.preference.PreferenceFragmentCompat

/**
 * Created by 23alot on 01.02.2020.
 * @link https://gitlab.com/terrakok/gitlab-client/blob/develop/app/src/main/java/ru/terrakok/gitlabclient/ui/global/BaseFragment.kt
 */

abstract class BasePreferenceFragment : PreferenceFragmentCompat() {

    companion object {
        private const val TAG = "BasePreferenceFragment"
    }

    abstract val layoutRes: Int

    abstract val shouldShowNavigationBar: Boolean

    private var instanceStateSaved: Boolean = false

    private val viewHandler = Handler()

    override fun onResume() {
        super.onResume()
        instanceStateSaved = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewHandler.removeCallbacksAndMessages(null)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        instanceStateSaved = true
    }

    open fun onBackPressed() {}
}
