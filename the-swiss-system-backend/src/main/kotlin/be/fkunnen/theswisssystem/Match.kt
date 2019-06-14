package be.fkunnen.theswisssystem

class Match constructor(val playerOne: Player, val playerTwo: Player, var result: MatchResult? = null) {

    fun enterResult(matchResult: MatchResult) {
        result = matchResult
        if (result!!.getWinner() == 1) {
            playerOne.increaseWins()
            playerTwo.increaseLosses()
        } else {
            playerOne.increaseLosses()
            playerTwo.increaseWins()
        }
    }

    fun getPlayers(): List<Player> {
        return listOf(playerOne, playerTwo)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Match

        if (playerOne == other.playerOne && playerTwo == other.playerTwo) return true
        if (playerTwo == other.playerOne && playerOne == other.playerTwo) return true

        return false
    }

    override fun hashCode(): Int {
        var result = playerOne.hashCode()
        result = 31 * result + playerTwo.hashCode()
        return result
    }


}
