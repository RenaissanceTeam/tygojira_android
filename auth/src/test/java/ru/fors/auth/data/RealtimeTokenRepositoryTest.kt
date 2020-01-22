package ru.fors.auth.data

import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import ru.fors.auth.api.data.TokenRepository.TokenEvent
import ru.fors.auth.data.repository.RealtimeTokenRepository
import kotlin.test.assertEquals

/**
 * Created by 23alot on 19.01.2020.
 */
object RealtimeTokenRepositoryTest : Spek({

    describe("Repository contains token realtime and refreshes it") {
        val repository = RealtimeTokenRepository()
        context("On start there is no token") {

            val tokenEvent = runBlocking { repository.requireToken() }

            it("Should return TokenEvent.NotExist") {
                assertEquals(TokenEvent.NotExist, tokenEvent)
            }
        }

        context("Should return token after refresh") {
            val token = "A2qA"
            val event = TokenEvent.Token(token = token)
            runBlocking { repository.setToken(token) }

            val tokenEvent = runBlocking { repository.requireToken() }

            it("Should return TokenEvent.Token(\"A2qA\")") {
                assertEquals(event, tokenEvent)
            }
        }
    }
})