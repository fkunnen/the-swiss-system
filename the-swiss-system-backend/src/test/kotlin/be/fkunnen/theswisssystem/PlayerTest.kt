package be.fkunnen.theswisssystem

import org.junit.Assert.assertEquals
import org.junit.Test

internal class PlayerTest {

    private val player = Player("Dan")

    @Test
    fun increaseLosses() {
        assertEquals(player.score, DummyScore(numberOfWins = 0, numberOfLosses = 0))

        player.increaseLosses()

        assertEquals(DummyScore(numberOfWins = 0, numberOfLosses = 1), player.score)
    }

    @Test
    fun increaseWins() {
        assertEquals(player.score, DummyScore(numberOfWins = 0, numberOfLosses = 0))

        player.increaseWins()

        assertEquals(DummyScore(numberOfWins = 1, numberOfLosses = 0), player.score)
    }
}
