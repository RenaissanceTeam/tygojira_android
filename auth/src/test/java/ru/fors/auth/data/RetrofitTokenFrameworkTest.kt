package ru.fors.auth.data

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.fors.auth.api.domain.dto.Credentials
import ru.fors.auth.api.domain.dto.TokenResponse
import ru.fors.auth.data.framework.RetrofitTokenFramework
import java.net.HttpURLConnection
import kotlin.test.assertEquals

/**
 * Created by 23alot on 20.01.2020.
 */
object RetrofitTokenFrameworkTest : Spek({
    val mockWebServer = MockWebServer()

    val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    describe("Получение token'a") {
        val b = retrofit.create(RetrofitTokenFramework::class.java)
        val body = TokenResponse(token = "123")

        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(gson.toJson(body, TokenResponse::class.java))

        mockWebServer.enqueue(response)

        it("Получает токен по данным пользователя") {
            val q = runBlocking { b.login(Credentials("a", "b")) }
            assertEquals(body, q)
        }
    }
})

