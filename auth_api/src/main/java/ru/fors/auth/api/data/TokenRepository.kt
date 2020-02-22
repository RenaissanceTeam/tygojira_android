package ru.fors.auth.api.data

/**
 * Created by 23alot on 19.01.2020.
 */
interface TokenRepository {

    suspend fun requireToken(): String

    suspend fun setToken(token: String)

}
