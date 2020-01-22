package ru.fors.auth.data.repository.auth

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.fors.auth.api.domain.dto.Credentials
import ru.fors.auth.api.domain.dto.TokenResponse
import ru.fors.auth.data.framework.RetrofitTokenFramework

/**
 * Created by 23alot on 22.01.2020.
 */
class RetrofitAuthRepository : AuthRepository {

    companion object {
        private const val url = "leha.com"
    }

    private val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(RetrofitTokenFramework::class.java)

    override suspend fun login(credentials: Credentials): TokenResponse {
        return retrofit.login(
            credentials = credentials
        )
    }

}