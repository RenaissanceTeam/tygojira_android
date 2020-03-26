package ru.fors.auth.data.repository

import ru.fors.auth.api.data.TokenRepository

/**
 * Created by 23alot on 19.01.2020.
 */
class RealtimeTokenRepository : TokenRepository {

    private var token: String? = null

    override suspend fun requireToken(): String {
        return token.orEmpty()
    }

    override suspend fun setToken(token: String) {
        this.token = token
    }

    override suspend fun discard() {
        token = null
    }

}