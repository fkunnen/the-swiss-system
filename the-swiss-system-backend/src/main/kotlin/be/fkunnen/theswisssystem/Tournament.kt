package be.fkunnen.theswisssystem

class Tournament constructor(val numberOfRounds: Int, var players: MutableList<Player> = mutableListOf()) {

    private val rounds = mutableListOf<Round>()
    private val matchGenerator = MatchGenerator(players)
    private lateinit var currentRound: Round
    private var tournamentStarted = false

    fun addPlayer(player: Player) {
        !tournamentStarted && players.add(player)
    }

    fun addPlayers(newPlayers: List<Player>) {
        !tournamentStarted && players.addAll(newPlayers)
    }

    fun startTournament() {
        val round = Round(matchGenerator.generate())

        rounds.add(round)
        currentRound = round
        tournamentStarted = true
    }

    fun startNewRound() {
        currentRound.finish()

        val round = Round(matchGenerator.generate())

        rounds.add(round)
        currentRound = round
    }

    fun endTournament() {

    }

    fun getRounds(): List<Round> {
        return rounds
    }
}
