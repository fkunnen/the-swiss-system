package be.fkunnen.theswisssystem

import org.junit.Test
import org.junit.Assert.*

class RandomMatchGeneratorTest {

    @Test
    fun generateFromEvenNumberOfPlayers() {
        val players = mutableListOf(Player("Dan"), Player("Frank"), Player("Geanina"), Player("Kenny"))
        val matchGenerator = RandomMatchGenerator(players)

        val matches = matchGenerator.generate()

        assertEquals(2, matches.size)
        val playersPlaying = setOf(matches.get(0).playerOne, matches.get(1).playerOne, matches.get(0).playerTwo, matches.get(1).playerTwo);
        assertEquals(4, playersPlaying.size)
    }

    @Test
    fun generateFromOddNumberOfPlayers() {
        val players = mutableListOf(Player("Dan"), Player("Frank"), Player("Geanina"), Player("Kenny"), Player("Geert"))
        val matchGenerator = RandomMatchGenerator(players)

        val matches = matchGenerator.generate()

        assertEquals(3, matches.size)
        val playersPlaying = mutableListOf<Player>()
        var jokerFound = false
        for (match in matches) {
            playersPlaying.add(match.playerOne)
            playersPlaying.add(match.playerTwo)
            if (match.playerOne.name == "Joker" || match.playerTwo.name == "Joker") jokerFound = true
        }
        assertEquals(6, playersPlaying.size)
        assertTrue(jokerFound);

    }
}
