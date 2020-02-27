package ru.fors.employees.presentation.view.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.fragment_employees.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.scope.currentScope
import ru.fors.employees.R
import ru.fors.employees.presentation.view.adapter.EmployeesAdapter
import ru.fors.employees.presentation.viewmodel.EmployeesViewModel
import ru.fors.employees.presentation.viewmodel.EmployeesViewState
import ru.fors.navigation.ui.BaseFragment

/**
 * Created by 23alot on 26.01.2020.
 */
@InternalCoroutinesApi
class EmployeesFragment : BaseFragment(), EmployeesAdapter.Listener {

    private val model: EmployeesViewModel by currentScope.inject()

    private lateinit var adapter: EmployeesAdapter

    override val layoutRes: Int
        get() = R.layout.fragment_employees

    override val shouldShowNavigationBar: Boolean
        get() = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = employees_toolbar.menu.findItem(R.id.action_search)
        employees_toolbar.setLogo(R.drawable.ic_fors_logo)
        employees_toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        search_view.setMenuItem(item)
        search_view.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                model.onQueryTextChanged(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }
        })
        adapter = EmployeesAdapter()
        adapter.listener = this
        employees_list.apply {
            adapter = this@EmployeesFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        lifecycleScope.launch {
            model.state
                .collect { updateState(it) }
        }
        model.onEmployeesRequired()
    }

    override fun onClick(position: Int) {
        model.onEmployeeSelected(position)
    }

    private fun updateState(state: EmployeesViewState) {
        adapter.items = state.showEmployees
        adapter.notifyDataSetChanged()
        state.selectedEmployee?.let { employee ->
            val fragment = EmployeeBottomSheetFragment.getInstance(employee)
            fragment.show(parentFragmentManager, "123")
        }
        model.onResetSelection()
    }

    override fun onDestroyView() {
        adapter.listener = null
        super.onDestroyView()
    }
}