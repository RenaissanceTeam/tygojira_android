package ru.fors.activities.di

import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.experimental.builder.scopedBy
import org.koin.experimental.builder.singleBy
import ru.fors.activities.api.data.ActivitiesRepository
import ru.fors.activities.api.domain.ActivitiesUseCase
import ru.fors.activities.data.repository.ActivitiesInterceptor
import ru.fors.activities.data.repository.RetrofitActivitiesRepository
import ru.fors.activities.domain.ActivitiesUseCaseImpl
import ru.fors.activities.presentation.view.fragment.ActivitiesFragment
import ru.fors.activities.presentation.viewmodel.ActivitiesViewModel

/**
 * Created by 23alot on 23.02.2020.
 */
@InternalCoroutinesApi
val activitiesModule = module {
    scope(named<ActivitiesFragment>()) {
        scopedBy<ActivitiesUseCase, ActivitiesUseCaseImpl>()

        viewModel { ActivitiesViewModel(get()) }
    }

    singleBy<ActivitiesRepository, RetrofitActivitiesRepository>()
    single { ActivitiesInterceptor(get()) }
}