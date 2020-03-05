package ru.fors.employees.di

import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.experimental.builder.scopedBy
import org.koin.experimental.builder.singleBy
import ru.fors.employees.api.data.EmployeesRepository
import ru.fors.employees.api.domain.EmployeesUseCase
import ru.fors.employees.data.repository.EmployeesInterceptor
import ru.fors.employees.data.repository.RetrofitEmployeesRepository
import ru.fors.employees.domain.EmployeesUseCaseImpl
import ru.fors.employees.presentation.view.fragment.EmployeesFragment
import ru.fors.employees.presentation.viewmodel.EmployeesViewModel

/**
 * Created by 23alot on 26.02.2020.
 */
@InternalCoroutinesApi
val employeesModule = module {
    scope(named<EmployeesFragment>()) {
        scopedBy<EmployeesUseCase, EmployeesUseCaseImpl>()

        viewModel { EmployeesViewModel(get()) }
    }

    singleBy<EmployeesRepository, RetrofitEmployeesRepository>()
    single { EmployeesInterceptor(get()) }
}