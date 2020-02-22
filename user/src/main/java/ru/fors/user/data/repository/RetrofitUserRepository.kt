package ru.fors.user.data.repository

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import ru.fors.auth.api.data.TokenRepository
import ru.fors.user.api.data.UserRepository
import ru.fors.user.api.domain.dto.Employee
import ru.fors.user.data.framework.RetrofitUserApi

/**
 * Created by 23alot on 22.02.2020.
 */
class RetrofitUserRepository(
    private val retrofit: Retrofit,
    private val userInterceptor: UserInterceptor
) : UserRepository {

    private val client = OkHttpClient.Builder()
        .addInterceptor(userInterceptor)
        .build()

    private val userApi = retrofit
        .newBuilder()
        .client(client)
        .build()
        .create(RetrofitUserApi::class.java)

    override suspend fun getUser(username: String): Employee = userApi.login(username).employee
}

class UserInterceptor(
    private val tokenRepository: TokenRepository
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenRepository.requireToken()
        }
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        return chain.proceed(request)
    }
}