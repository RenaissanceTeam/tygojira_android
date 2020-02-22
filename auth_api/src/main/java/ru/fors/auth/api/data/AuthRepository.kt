package ru.fors.auth.api.data

import ru.fors.auth.api.domain.dto.Credentials
import ru.fors.auth.api.domain.dto.TokenResponse

/**
 * Created by 23alot on 22.01.2020.
 */
interface AuthRepository {

    suspend fun login(credentials: Credentials): TokenResponse

}