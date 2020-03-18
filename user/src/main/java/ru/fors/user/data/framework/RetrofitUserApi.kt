package ru.fors.user.data.framework

import retrofit2.http.GET
import retrofit2.http.Path
import ru.fors.user.api.domain.dto.Employee

/**
 * Created by 23alot on 22.02.2020.
 */
interface RetrofitUserApi {
    @GET("api/employees/profile")
    suspend fun profile(): Employee
}