package ru.fors.employees.presentation.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_employee.*
import ru.fors.employees.R
import ru.fors.user.api.domain.dto.Employee


/**
 * Created by 23alot on 27.02.2020.
 */
class EmployeeBottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        fun getInstance(employee: Employee): EmployeeBottomSheetFragment {
            val fragment = EmployeeBottomSheetFragment()
            val bundle = Bundle().apply {
                putString(FIRST_NAME, employee.firstName)
                putString(LAST_NAME, employee.lastName)
                putString(MIDDLE_NAME, employee.middleName)
                putLong(ID, employee.id)
                putString(POSITION, employee.position)
                putString(SUBDIVISION, employee.subdivision)
                putStringArray(SKILLS, employee.skills.toTypedArray())
            }
            fragment.arguments = bundle
            return fragment
        }

        private const val FIRST_NAME = "FIRST_NAME"
        private const val LAST_NAME = "LAST_NAME"
        private const val MIDDLE_NAME = "MIDDLE_NAME"
        private const val ID = "ID"
        private const val POSITION = "POSITION"
        private const val SKILLS = "SKILLS"
        private const val SUBDIVISION = "SUBDIVISION"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_employee, container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            user_first_name.text = bundle.getString(FIRST_NAME)
            user_middle_name.text = bundle.getString(MIDDLE_NAME)
            user_last_name.text = bundle.getString(LAST_NAME)
            user_position.text = bundle.getString(POSITION)
            user_skills.text = bundle.getStringArray(SKILLS)?.joinToString()
            user_subdivision.text = bundle.getString(SUBDIVISION)
        }
    }
}