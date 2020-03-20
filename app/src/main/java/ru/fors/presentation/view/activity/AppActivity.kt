package ru.fors.presentation.view.activity

import android.graphics.Rect
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.doOnApplyWindowInsets
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import ru.fors.R
import ru.fors.navigation.ui.BaseFragment
import ru.fors.navigation.ui.BasePreferenceFragment
import ru.fors.presentation.viewmodel.AppViewModel
import ru.fors.presentation.viewmodel.AppViewState
import ru.fors.presentation.viewmodel.Theme
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

/**
 * Created by 23alot on 26.01.2020.
 */
class AppActivity : AppCompatActivity() {
    private val navigatorHolder: NavigatorHolder by inject()
    private val model: AppViewModel by inject()
    private val lifecycleCallbacks by lazy {
        LifecycleCallbacks()
    }

    private val navigator: Navigator =
        object : SupportAppNavigator(this, supportFragmentManager, R.id.container) {
            override fun setupFragmentTransaction(
                command: Command?,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                // Fix incorrect order lifecycle callback of MainFragment
                fragmentTransaction.setReorderingAllowed(true)
            }
        }

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.registerFragmentLifecycleCallbacks(lifecycleCallbacks, true)

        findViewById<View>(R.id.container).doOnApplyWindowInsets { view, insets, initialPadding ->
            view.updatePadding(
                left = initialPadding.left + insets.systemWindowInsetLeft,
                right = initialPadding.right + insets.systemWindowInsetRight
            )
            insets.replaceSystemWindowInsets(
                Rect(
                    0,
                    insets.systemWindowInsetTop,
                    0,
                    insets.systemWindowInsetBottom
                )
            )
        }
        setupBottomNavigation()

        GlobalScope.launch(Dispatchers.Main) {
            model.state
                .collect { updateState(it) }
        }

        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)
        model.observeDarkModeChanges()
        currentFragment ?: model.startAuthFlow()

    }

    private fun setupBottomNavigation() {
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnNavigationItemSelectedListener { menuItem ->
            val id = menuItem.itemId
            when(id) {
                R.id.action_user -> model.onUserNavigation()
                R.id.action_activities -> model.onActivitiesNavigation()
                R.id.action_settings -> model.onSettingsNavigation()
            }
            true
        }
    }

    private fun updateState(state: AppViewState) {
        findViewById<View>(R.id.bottom_navigation).visibility =
            if (state.shouldShowBottomNavigation) {
                View.VISIBLE
            } else {
                View.GONE
            }

        when(state.theme) {
            Theme.DARK -> AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
            Theme.LIGHT -> AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        }

    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        model.onBack()
    }

    override fun onDestroy() {
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(lifecycleCallbacks)
        super.onDestroy()
    }

    inner class LifecycleCallbacks : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
            super.onFragmentResumed(fm, f)
            val shouldShow = (f as? BaseFragment)?.shouldShowNavigationBar ?: (f as? BasePreferenceFragment)?.shouldShowNavigationBar ?: return
            model.onScreenShown(shouldShow)
        }
    }
}