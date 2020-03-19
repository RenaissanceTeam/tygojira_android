package ru.fors.activities.data.repository

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import ru.fors.activities.api.data.ActivitiesRepository
import ru.fors.activities.api.domain.dto.Activity
import ru.fors.activities.api.domain.dto.LeadItem
import ru.fors.activities.api.domain.dto.Workload
import ru.fors.activities.data.framework.RetrofitActivitiesApi
import ru.fors.auth.api.data.TokenRepository

/**
 * Created by 23alot on 23.02.2020.
 */
class RetrofitActivitiesRepository(
    private val retrofit: Retrofit,
    private val activitiesInterceptor: ActivitiesInterceptor
) : ActivitiesRepository {

    private val client = OkHttpClient.Builder()
        .addInterceptor(activitiesInterceptor)
        .build()

    private val activitiesApi = retrofit
        .newBuilder()
        .client(client)
        .build()
        .create(RetrofitActivitiesApi::class.java)

    override suspend fun getActivities(id: Long): List<Workload> = activitiesApi.workloads(id).workloads

}

class ActivitiesInterceptor(
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