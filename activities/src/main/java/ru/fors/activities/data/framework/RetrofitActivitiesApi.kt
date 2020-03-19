package ru.fors.activities.data.framework

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by 23alot on 23.02.2020.
 */
interface RetrofitActivitiesApi {
    @GET("api/employees/{id}/workload")
    suspend fun workloads(
        @Path("id") id: Long
    ): WorkloadResponse
}