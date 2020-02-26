package ru.fors.employees.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import ru.fors.auth.api.data.TokenRepository
import ru.fors.employees.api.data.EmployeesRepository
import ru.fors.employees.data.framework.RetrofitEmployeesApi
import ru.fors.user.api.domain.dto.Employee

/**
 * Created by 23alot on 26.02.2020.
 */
class RetrofitEmployeesRepository(
    private val retrofit: Retrofit,
    private val employeesInterceptor: EmployeesInterceptor
) : EmployeesRepository {

    private val client = OkHttpClient.Builder()
        .addInterceptor(employeesInterceptor)
        .build()

    private val employeesApi = retrofit
        .newBuilder()
        .client(client)
        .build()
        .create(RetrofitEmployeesApi::class.java)

    override suspend fun getEmployees(): Flow<List<Employee>> = flow<List<Employee>> {
        val first = employeesApi.employees(0, 25)
        emit(first.items)
        val totalPages = first.totalPages
        var page = 1
        while (page != totalPages) {
            val response = employeesApi.employees(1, 25)
            page = response.currentPage + 1
            emit(response.items)
        }
    }
}

class EmployeesInterceptor(
    private val tokenRepository: TokenRepository
) : Interceptor {
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