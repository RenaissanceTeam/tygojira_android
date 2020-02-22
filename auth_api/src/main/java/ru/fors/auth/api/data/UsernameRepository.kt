package ru.fors.auth.api.data

/**
 * Created by 23alot on 22.02.2020.
 */
interface UsernameRepository {

    suspend fun requireUsername(): String

    suspend fun setUsername(username: String)
}