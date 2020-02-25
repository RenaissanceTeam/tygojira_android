package ru.fors.activities.data.repository

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import ru.fors.activities.api.data.ActivitiesRepository
import ru.fors.activities.api.domain.dto.Activity
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

    private val mock = listOf<Activity>(
        Activity(
            id = 0,
            name = "test1",
            startDate = "2020-02-12",
            endDate = "2020-02-27"
        ),
        Activity(
            id = 1,
            name = "test2",
            startDate = "2020-02-13",
            endDate = "2020-02-28"
        ),
        Activity(
            id = 2,
            name = "test3",
            startDate = "2020-02-10",
            endDate = "2020-02-27"
        ),
        Activity(
            id = 3,
            name = "test4",
            startDate = "2020-02-08",
            endDate = "2020-02-29"
        )
    )

    override suspend fun getActivities(): List<Activity> {
        return mock
    }
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