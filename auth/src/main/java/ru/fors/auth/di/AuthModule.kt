package ru.fors.auth.di

import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.experimental.builder.scopedBy
import org.koin.experimental.builder.singleBy
import ru.fors.auth.api.data.TokenRepository
import ru.fors.auth.api.domain.SignInUseCase
import ru.fors.auth.data.repository.RealtimeTokenRepository
import ru.fors.auth.data.repository.auth.AuthRepository
import ru.fors.auth.data.repository.auth.RetrofitAuthRepository
import ru.fors.auth.domain.SignInUseCaseImpl
import ru.fors.auth.presentation.view.fragment.AuthFragment
import ru.fors.auth.presentation.viewmodel.AuthViewModel

/**
 * Created by 23alot on 22.01.2020.
 */

@InternalCoroutinesApi
val authModule = module {
    scope(named<AuthFragment>()) {
        scoped<AuthRepository> { RetrofitAuthRepository(get()) }
        scoped<TokenRepository> { RealtimeTokenRepository() }
        scopedBy<SignInUseCase, SignInUseCaseImpl>()

        viewModel { AuthViewModel(get(), get()) }
    }
}