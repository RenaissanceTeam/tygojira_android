package ru.fors.navigation.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.core.scope.isScopeOpen
import ru.terrakok.cicerone.objectScopeName

/**
 * Created by 23alot on 01.02.2020.
 * @link https://gitlab.com/terrakok/gitlab-client/blob/develop/app/src/main/java/ru/terrakok/gitlabclient/ui/global/BaseFragment.kt
 */
private const val PROGRESS_TAG = "bf_progress"
private const val STATE_SCOPE_NAME = "state_scope_name"

abstract class BaseFragment : Fragment() {

    companion object {
        private const val TAG = "BaseFragment"
    }

    abstract val layoutRes: Int

    private var instanceStateSaved: Boolean = false

    private val viewHandler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(layoutRes, container, false)

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
