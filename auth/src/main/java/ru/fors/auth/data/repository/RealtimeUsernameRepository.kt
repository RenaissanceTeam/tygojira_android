package ru.fors.auth.data.repository

import ru.fors.auth.api.data.UsernameRepository

/**
 * Created by 23alot on 22.02.2020.
 */
class RealtimeUsernameRepository : UsernameRepository {
    private var username: String? = null

    override suspend fun requireUsername(): String = username ?: ""

    override suspend fun setUsername(username: String) {
        this.username = username
    }
}