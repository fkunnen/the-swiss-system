package be.fkunnen.theswisssystem

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
class Routes @Autowired constructor(var tournamentService: TournamentService) {

    @Bean
    fun routingFunction() = router {
        (accept(MediaType.APPLICATION_JSON)).nest {
            POST("/tournament", tournamentService::createDummyTournament)
        }
    }
}
