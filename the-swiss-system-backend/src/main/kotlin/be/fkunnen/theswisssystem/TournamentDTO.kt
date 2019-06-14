package be.fkunnen.theswisssystem

data class TournamentDTO constructor(
        var numberOfRounds: Int,
        var players: List<Player>,
        var scheduledRounds: List<Round>,
        var currentRound: Round,
        var tournamentStarted: Boolean,
        var tournamentFinished: Boolean )

fun Tournament.toDTO() : TournamentDTO {
    return TournamentDTO(
            this.getNumberOfRounds(),
            this.getPlayers(),
            this.getScheduledRounds(),
            this.getCurrentRound(),
            this.isTournamentStarted(),
            this.isTournamentFinished()
    )
}
