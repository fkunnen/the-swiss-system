package be.fkunnen.theswisssystem

object TestConstants {
    val PLAYER_FRANK = Player("Frank")
    val PLAYER_DAN = Player("Dan")

    val MATCH_BETWEEN_FRANK_AND_DAN = Match(PLAYER_FRANK, PLAYER_DAN)
    val PLAYERS_FRANK_DAN_LIST = listOf(PLAYER_FRANK, PLAYER_DAN)
}
