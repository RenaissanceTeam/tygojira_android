package ru.fors.auth.api.data

/**
 * Created by 23alot on 19.01.2020.
 */
interface TokenRepository {
    sealed class TokenEvent {
        object NotExist : TokenEvent()

        data class Token(val token: String) : TokenEvent()
    }

    suspend fun requireToken(): TokenEvent

    suspend fun setToken(token: String)
}
