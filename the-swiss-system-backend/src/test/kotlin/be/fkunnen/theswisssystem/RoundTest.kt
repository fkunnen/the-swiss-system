package be.fkunnen.theswisssystem

import be.fkunnen.theswisssystem.TestConstants.PLAYER_DAN
import be.fkunnen.theswisssystem.TestConstants.PLAYER_FRANK
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertFailsWith

class RoundTest {

    @Test
    fun addMatchResult() {
        val match = Match(PLAYER_FRANK, PLAYER_DAN)
        val round = Round(listOf(match))

        val matchResult = MatchResult(listOf("21-16", "21-12"))
        round.addMatchResult(match, matchResult)

        assertEquals(matchResult, round.matches[0].result)
    }

    @Test
    fun finish_notAllMatchesHaveBeenPlayedYet() {
        val match = Match(PLAYER_FRANK, PLAYER_DAN)
        val round = Round(listOf(match))

        assertFailsWith<IllegalStateException> {
            round.finish()
        }
    }

    @Test
    fun finish_allMatchesHaveBeenPlayed() {
        val match = Match(PLAYER_FRANK, PLAYER_DAN)
        val round = Round(listOf(match))

        val matchResult = MatchResult(listOf("21-16", "21-12"))
        round.addMatchResult(match, matchResult)

        round.finish()

        val ranking = round.getRanking()
        assertEquals(PLAYER_FRANK, ranking[0])
        assertEquals(PLAYER_DAN, ranking[1])
    }
}
