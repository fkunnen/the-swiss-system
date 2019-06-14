package be.fkunnen.theswisssystem

import be.fkunnen.theswisssystem.TestConstants.MATCH_BETWEEN_FRANK_AND_DAN
import org.junit.Assert.assertEquals
import org.junit.Test

class MatchTest {

    private var match: Match = MATCH_BETWEEN_FRANK_AND_DAN

    @Test
    internal fun enterResult() {
        match.enterResult(MatchResult(listOf("21-16", "21-12")))

        assertEquals(match.result?.games?.size, 2)
        assertEquals(DummyScore(1, 0), match.playerOne.score);
        assertEquals(DummyScore(0, 1), match.playerTwo.score);
    }

}
