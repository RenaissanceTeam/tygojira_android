package ru.fors

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object ExampleSpekTest : Spek({
    describe("Arithmetic test") {
        it("Result of 2+2. Should return 4") {
            assertEquals(expected = 4, actual = 2 + 2)
        }
    }
})
