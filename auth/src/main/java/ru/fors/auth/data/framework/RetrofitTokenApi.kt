package ru.fors.auth.data.framework

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import ru.fors.auth.api.domain.dto.Credentials
import ru.fors.auth.api.domain.dto.TokenResponse

/**
 * Created by 23alot on 20.01.2020.
 */
interface RetrofitTokenApi {
    @POST("api/login")
    suspend fun login(@Body credentials: Credentials): TokenResponse
}