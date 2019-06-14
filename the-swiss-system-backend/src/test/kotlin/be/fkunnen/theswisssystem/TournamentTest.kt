package be.fkunnen.theswisssystem

import be.fkunnen.theswisssystem.TestConstants.MATCH_BETWEEN_FRANK_AND_DAN
import be.fkunnen.theswisssystem.TestConstants.PLAYERS_FRANK_DAN_LIST
import be.fkunnen.theswisssystem.TestConstants.PLAYER_DAN
import be.fkunnen.theswisssystem.TestConstants.PLAYER_FRANK
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith

class TournamentTest {

    private val tournament: Tournament = Tournament(numberOfRounds = 1);
    private var matchGenerator = mockk<RandomMatchGenerator>()

    @Before
    fun setup() {
        every { matchGenerator.generate() } returns listOf(MATCH_BETWEEN_FRANK_AND_DAN)
        tournament.addPlayers(PLAYERS_FRANK_DAN_LIST)
    }

    @Test
    fun addPlayer() {
        val tournament: Tournament = Tournament(numberOfRounds = 2);
        var tournamentPlayers = tournament.getPlayers();
        assertEquals(tournamentPlayers.size, 0);

        tournament.addPlayer(Player(name = "Frank"));

        tournamentPlayers = tournament.getPlayers();
        assertEquals(tournamentPlayers.size, 1);
    }

    @Test
    fun addPlayers() {
        val tournament: Tournament = Tournament(numberOfRounds = 2);
        var tournamentPlayers = tournament.getPlayers();
        assertEquals(tournamentPlayers.size, 0);

        tournament.addPlayers(listOf(Player(name = "Frank"), Player("Dan")));

        tournamentPlayers = tournament.getPlayers();
        assertEquals(tournamentPlayers.size, 2);
    }

    @Test
    fun startTournament() {
        var rounds = tournament.getRounds()
        assertEquals(0, rounds.size)

        tournament.startTournament()

        rounds = tournament.getRounds()
        assertEquals(1, rounds.size)
        assertEquals(1, rounds[0].matches.size)
    }

    @Test
    fun startNewRound() {
        tournament.startTournament()
        tournament.getRounds()[0].addMatchResult(Match(PLAYER_FRANK, PLAYER_DAN), MatchResult(listOf("21-16", "21-12")))

        tournament.startNewRound()

        assertEquals(2, tournament.getRounds().size)
    }

    @Test
    fun endTournament() {
        tournament.startTournament()
        tournament.getRounds()[0].addMatchResult(Match(PLAYER_FRANK, PLAYER_DAN), MatchResult(listOf("21-16", "21-12")))

        tournament.endTournament();

        val ranking = tournament.getRanking()
        assertEquals(2, ranking.size)
        assertEquals(tournament.getRounds().size, tournament.getNumberOfRounds())
    }

    @Test
    fun endTournament_NotAllMatchesPlayed() {
        tournament.startTournament()

        assertFailsWith<IllegalStateException> {
            tournament.endTournament();
        }
    }

    @Test
    fun endTournament_NotAllRoundsHaveBeenScheduled() {
        val tournament = Tournament(numberOfRounds = 2)
        tournament.addPlayers(PLAYERS_FRANK_DAN_LIST)
        tournament.startTournament()
        tournament.getRounds()[0].addMatchResult(Match(PLAYER_FRANK, PLAYER_DAN), MatchResult(listOf("21-16", "21-12")))

        assertFailsWith<IllegalStateException> {
            tournament.endTournament();
        }
    }

    @Test
    fun demoPlay() {
        val tournament = Tournament(numberOfRounds = 4)

        tournament.addPlayers(listOf(
                Player("Frank"),
                Player("Dan"),
                Player("Kenny"),
                Player("Geanina"),
                Player("Jef"),
                Player("Matthias"),
                Player("Kai"),
                Player("Sonja"),
                Player("Felix"),
                Player("Karel"),
                Player("Guido"),
                Player("Jonathan"),
                Player("Rudi"),
                Player("Johnny"),
                Player("Wolfram"),
                Player("Tung")
        ))

        tournament.demoPlay()

        tournament.getRanking().forEach { println(it) }
    }

    @Test
    fun serialize() {
        val tournament = Tournament(numberOfRounds = 4)

        tournament.addPlayers(listOf(
                Player("Frank"),
                Player("Dan"),
                Player("Kenny"),
                Player("Geanina"),
                Player("Jef"),
                Player("Matthias"),
                Player("Kai"),
                Player("Sonja"),
                Player("Felix"),
                Player("Karel"),
                Player("Guido"),
                Player("Jonathan"),
                Player("Rudi"),
                Player("Johnny"),
                Player("Wolfram"),
                Player("Tung")
        ))

        tournament.demoPlay()

        print(tournament.serialize())
    }
}
