package be.fkunnen.theswisssystem

import org.junit.Assert.assertEquals
import org.junit.Test

internal class MatchResultTest {

    private var matchResult = MatchResult(listOf("21-16", "21-12"))

    @Test
    internal fun getWinner() {
        val playerNumber = matchResult.getWinner()

        assertEquals(1, playerNumber)
    }
}
