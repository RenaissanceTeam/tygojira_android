package ru.fors.auth.data.repository

import ru.fors.auth.api.data.TokenRepository
import ru.fors.auth.api.data.TokenRepository.TokenEvent

/**
 * Created by 23alot on 19.01.2020.
 */
class RealtimeTokenRepository : TokenRepository {

    private var token: String? = null

    override suspend fun requireToken(): TokenEvent {
        return token?.let(TokenEvent::Token) ?: TokenEvent.NotExist
    }

    override suspend fun setToken(token: String) {
        this.token = token
    }

}