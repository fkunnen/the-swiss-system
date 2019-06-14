package be.fkunnen.theswisssystem

import be.fkunnen.theswisssystem.TestConstants.MATCH_BETWEEN_FRANK_AND_DAN
import be.fkunnen.theswisssystem.TestConstants.PLAYERS_FRANK_DAN_LIST
import be.fkunnen.theswisssystem.TestConstants.PLAYER_DAN
import be.fkunnen.theswisssystem.TestConstants.PLAYER_FRANK
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class TournamentTest {

    private val tournament: Tournament = Tournament(numberOfRounds = 5);

    @Test
    fun addPlayer() {
        var tournamentPlayers = tournament.players;
        assertEquals(tournamentPlayers.size, 0);

        tournament.addPlayer(Player(name = "Frank"));

        tournamentPlayers = tournament.players;
        assertEquals(tournamentPlayers.size, 1);
    }

    @Test
    fun addPlayers() {
        var tournamentPlayers = tournament.players;
        assertEquals(tournamentPlayers.size, 0);

        tournament.addPlayers(listOf(Player(name = "Frank"), Player("Dan")));

        tournamentPlayers = tournament.players;
        assertEquals(tournamentPlayers.size, 2);
    }

    @Test
    fun startTournament() {
        tournament.addPlayers(PLAYERS_FRANK_DAN_LIST)
        var rounds = tournament.getRounds()
        assertEquals(0, rounds.size)

        tournament.startTournament()

        rounds = tournament.getRounds()
        assertEquals(1, rounds.size)
        assertEquals(1, rounds[0].matches.size)
    }

    @Test
    fun startNewRound() {
        val matchGenerator = mockk<MatchGenerator>()
        every { matchGenerator.generate() } returns listOf(MATCH_BETWEEN_FRANK_AND_DAN)
        tournament.addPlayers(PLAYERS_FRANK_DAN_LIST)
        tournament.startTournament()
        tournament.getRounds()[0].addMatchResult(Match(PLAYER_FRANK, PLAYER_DAN), MatchResult(listOf("21-16", "21-12")))

        tournament.startNewRound()

        assertEquals(2, tournament.getRounds().size);
    }

    @Test
    fun endTournament() {

    }
}
