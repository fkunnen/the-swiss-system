package be.fkunnen.theswisssystem

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono
import java.lang.IllegalArgumentException

@Service
class TournamentService @Autowired constructor() {

    var tournament: Tournament? = null

    fun createDummyTournament(request: ServerRequest): Mono<ServerResponse> {

        return request.bodyToMono(TournamentSetupDTO::class.java)
                .doOnNext{
                    if (it.numberOfRounds >= it.players.size) throw IllegalArgumentException("The number of rounds can be maximum numberOfPlayers - 1")

                    val players = it.players.map { Player(it) }
                    createTournament(it, players)
                }
                .flatMap { ok().body(BodyInserters.fromObject(tournament)) }
    }

    fun createTournament(tournamentSetup : TournamentSetupDTO, players: List<Player>) {
        tournament = Tournament(numberOfRounds = tournamentSetup.numberOfRounds, players = players)
        tournament?.demoPlay()
    }
}
