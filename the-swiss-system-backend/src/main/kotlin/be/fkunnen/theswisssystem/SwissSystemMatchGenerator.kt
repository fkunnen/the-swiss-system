package be.fkunnen.theswisssystem

class SwissSystemMatchGenerator constructor(private val players: MutableList<Player>) {

    fun generate(): List<Match> {
        addTheJokerIfNecessary()

        val matches = mutableListOf<Match>()

        val scoreToPlayerMap = players.asSequence()
                .groupBy { it.score }

        for (dummyScore in scoreToPlayerMap.keys) {
            val listOfPlayersInScoreGroup = scoreToPlayerMap[dummyScore]!!
            val numberOfGamesToScheduleForThisGroup = listOfPlayersInScoreGroup.size / 2

            for ( i in 0 until numberOfGamesToScheduleForThisGroup) {
                val match = Match(listOfPlayersInScoreGroup[i], listOfPlayersInScoreGroup[i + numberOfGamesToScheduleForThisGroup])
                matches.add(match)
            }
        }

        return matches
    }

    private fun addTheJokerIfNecessary() {
        if (players.size % 2 == 1) {
            players.add(Player("Joker"))
        }
    }
}
