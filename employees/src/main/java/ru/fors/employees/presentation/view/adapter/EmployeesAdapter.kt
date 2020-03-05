package ru.fors.employees.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_employee.view.*
import ru.fors.employees.R
import ru.fors.user.api.domain.dto.Employee

/**
 * Created by 23alot on 27.02.2020.
 */
class EmployeesAdapter : RecyclerView.Adapter<EmployeesAdapter.EmployeesViewHolder>() {

    interface Listener {
        fun onClick(position: Int)
    }

    var items: List<Employee> = listOf()
    var listener: Listener? = null

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = items[position].id

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_employee, parent, false)
        return EmployeesViewHolder(view)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: EmployeesViewHolder, position: Int) {
        holder.bind(items[position])
        holder.view.setOnClickListener { listener?.onClick(position) }
    }

    class EmployeesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(employee: Employee) {
            view.employee_first_name.text = employee.firstName
            view.employee_middle_name.text = employee.middleName
            view.employee_last_name.text = employee.lastName
        }
    }
}