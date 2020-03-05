package ru.fors.other.presentation.view.fragment

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.fragment_other.*
import org.koin.android.scope.currentScope
import ru.fors.navigation.ui.BaseFragment
import ru.fors.other.R
import ru.fors.other.presentation.viewmodel.OtherViewModel

/**
 * Created by 23alot on 24.02.2020.
 */
class OtherFragment : BaseFragment() {

    private val model: OtherViewModel by currentScope.inject()

    override val layoutRes: Int
        get() = R.layout.fragment_other

    override val shouldShowNavigationBar: Boolean
        get() = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activities_toolbar.setLogo(R.drawable.ic_fors_logo)

        activities_toolbar.setOnMenuItemClickListener { item: MenuItem? ->
            when(item?.itemId) {
                R.id.action_settings -> model.onSettingsClicked()
                else -> return@setOnMenuItemClickListener false
            }
            true
        }

        other_employees_list.setOnClickListener { model.onEmployeesClick() }
    }
}