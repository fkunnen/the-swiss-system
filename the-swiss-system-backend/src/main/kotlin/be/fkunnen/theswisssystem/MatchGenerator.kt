package be.fkunnen.theswisssystem

class MatchGenerator constructor(private val players: MutableCollection<Player>) {

    fun generate(): List<Match> {
        addTheJokerIfNecessary()

        val shuffled = players.shuffled()
        val matches = mutableListOf<Match>();
        for (i in 0 until shuffled.size step 2) {
            matches.add(Match(shuffled[i], shuffled[i+1]))
        }
        return matches
    }

    private fun addTheJokerIfNecessary() {
        if (players.size % 2 == 1) {
            players.add(Player("Joker"))
        }
    }

}
