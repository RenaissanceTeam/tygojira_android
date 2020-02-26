package ru.fors.employees.data.framework

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by 23alot on 26.02.2020.
 */
interface RetrofitEmployeesApi {
    @GET("api/employees/")
    suspend fun employees(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): EmployeesResponse
}