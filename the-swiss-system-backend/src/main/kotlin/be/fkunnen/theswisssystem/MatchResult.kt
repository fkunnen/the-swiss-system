package be.fkunnen.theswisssystem

data class MatchResult constructor(val games: List<String>) {

    fun getWinner(): Int {
        var winsPlayerOne = 0
        var winsPlayerTwo = 0

        games.forEach {
            val pointsFirstPlayer = it.substringBeforeLast("-")
            val pointsSecondPlayer = it.substringAfterLast("-");

            if(Integer.parseInt(pointsFirstPlayer) > Integer.parseInt(pointsSecondPlayer)) {
                winsPlayerOne++
            } else {
                winsPlayerTwo++
            }
        }

        if (winsPlayerOne > winsPlayerTwo) {
            return 1
        } else {
            return 2
        }
    }
}
