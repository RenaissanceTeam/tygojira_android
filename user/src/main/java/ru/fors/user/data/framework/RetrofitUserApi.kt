package ru.fors.user.data.framework

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by 23alot on 22.02.2020.
 */
interface RetrofitUserApi {
    @GET("api/employees/users/{username}")
    suspend fun login(@Path("username") username: String): UserResponse
}