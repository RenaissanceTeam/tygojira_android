package ru.fors.di

import org.koin.dsl.module
import ru.fors.auth.api.data.TokenRepository
import ru.fors.auth.data.repository.RealtimeTokenRepository
import ru.fors.auth.data.repository.auth.AuthRepository
import ru.fors.auth.data.repository.auth.RetrofitAuthRepository

/**
 * Created by 23alot on 22.01.2020.
 */

val authModule = module {
    single<AuthRepository> { RetrofitAuthRepository(get()) }
    single<TokenRepository> { RealtimeTokenRepository() }
}