package ru.fors.user.di

import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.experimental.builder.scopedBy
import org.koin.experimental.builder.singleBy
import ru.fors.user.api.data.UserRepository
import ru.fors.user.api.domain.UserUseCase
import ru.fors.user.data.repository.RetrofitUserRepository
import ru.fors.user.data.repository.UserInterceptor
import ru.fors.user.domain.UserUseCaseImpl
import ru.fors.user.presentation.view.fragment.UserFragment
import ru.fors.user.presentation.viewmodel.UserViewModel

/**
 * Created by 23alot on 22.02.2020.
 */
@InternalCoroutinesApi
val userModule = module {
    scope(named<UserFragment>()) {
        scopedBy<UserUseCase, UserUseCaseImpl>()

        viewModel { UserViewModel(get()) }
    }

    singleBy<UserRepository, RetrofitUserRepository>()
    single { UserInterceptor(get()) }
}