package be.fkunnen.theswisssystem

import com.google.gson.Gson
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json

class Tournament constructor(val numberOfRounds: Int, var players: MutableList<Player> = mutableListOf()) {

    private val scheduledRounds = mutableListOf<Round>()
    val randomMatchGenerator = RandomMatchGenerator(players)
    val swissSystemMatchGenerator = SwissSystemMatchGenerator(players)
    private lateinit var currentRound: Round
    private var tournamentStarted = false
    private var tournamentFinished = false

    fun addPlayer(player: Player) {
        !tournamentStarted && players.add(player)
    }

    fun addPlayers(newPlayers: List<Player>) {
        !tournamentStarted && players.addAll(newPlayers)
    }

    fun startTournament() {
        val round = Round(randomMatchGenerator.generate())

        scheduledRounds.add(round)
        currentRound = round
        tournamentStarted = true
    }

    fun startNewRound() {
        if (numberOfRounds == scheduledRounds.size) endTournament()

        currentRound.finish()

        val round = Round(swissSystemMatchGenerator.generate())

        scheduledRounds.add(round)
        currentRound = round
    }

    fun endTournament() {
        if (numberOfRounds > scheduledRounds.size) throw IllegalStateException("There are still unscheduled rounds !!")

        currentRound.finish()
        tournamentFinished = true
    }

    fun getRanking(): List<Player> {
        return currentRound.getRanking();
    }

    fun getRounds(): List<Round> {
        return scheduledRounds
    }

    fun demoPlay(): Tournament { // 16 players
        startTournament()
        enterResultsForEveryMatchInTheCurrentRound()

        startNewRound()
        enterResultsForEveryMatchInTheCurrentRound()

        startNewRound()
        enterResultsForEveryMatchInTheCurrentRound()

        startNewRound()
        enterResultsForEveryMatchInTheCurrentRound()

        endTournament()

        return this
    }

    fun serialize(): String {
        return Gson().toJson(this)
    }

    private fun enterResultsForEveryMatchInTheCurrentRound() {
        currentRound.matches.forEach { currentRound.addMatchResult(it, randomMatchResult()) }
    }

    private fun randomMatchResult(): MatchResult {
        return MatchResult(listOf("21-16", "21-12"))
    }
}
