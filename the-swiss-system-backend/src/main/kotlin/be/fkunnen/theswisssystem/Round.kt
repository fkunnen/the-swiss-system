package be.fkunnen.theswisssystem

class Round constructor(val matches: List<Match>) {

    private var ranking = listOf<Player>()

    fun addMatchResult(match: Match, matchResult: MatchResult) {
        matches.first { it == match }.enterResult(matchResult)
    }

    fun finish() {
        if (matches.any { it.result == null } ) throw IllegalStateException("There are still unplayed matches !!")

        val players = mutableSetOf<Player>()
        matches.forEach { players.addAll(it.getPlayers()) }
        ranking = players.sortedByDescending { it.score.numberOfWins }
    }

    fun getRanking(): List<Player> {
        return ranking
    }
}
