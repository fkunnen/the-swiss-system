package be.fkunnen.theswisssystem

import org.junit.Test

import org.junit.Assert.*

class SwissSystemMatchGeneratorTest {

    @Test
    fun generate() {

        val players = mutableListOf(Player("Dan"), Player("Frank"), Player("Geanina"), Player("Kenny"))
        val matchGenerator = SwissSystemMatchGenerator(players)

        val matches = matchGenerator.generate()

        assertEquals(2, matches.size)
        assertEquals(Match(Player("Dan"), Player("Geanina")), matches[0])
        assertEquals(Match(Player("Frank"), Player("Kenny")), matches[1])
    }
}
