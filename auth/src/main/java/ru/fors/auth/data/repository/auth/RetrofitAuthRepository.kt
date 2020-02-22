package ru.fors.auth.data.repository.auth

import kotlinx.coroutines.runBlocking
import okhttp3.*
import retrofit2.Retrofit
import ru.fors.auth.api.data.AuthRepository
import ru.fors.auth.api.data.TokenRepository
import ru.fors.auth.api.domain.dto.Credentials
import ru.fors.auth.api.domain.dto.TokenResponse
import ru.fors.auth.data.framework.RetrofitTokenApi

/**
 * Created by 23alot on 22.01.2020.
 */
class RetrofitAuthRepository(
    private val retrofit: Retrofit
) : AuthRepository {

    private val signIn = retrofit
        .create(RetrofitTokenApi::class.java)

    override suspend fun login(credentials: Credentials): TokenResponse {
        return signIn.login(
            credentials = credentials
        )
    }
}
