package be.fkunnen.theswisssystem

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.netty.http.server.HttpServer

@Configuration
@EnableAutoConfiguration
class HttpServerConfig @Autowired constructor() {

    @Bean
    fun httpServer(routingFunction: RouterFunction<ServerResponse>): HttpServer {
        val httpHandler = RouterFunctions.toHttpHandler(routingFunction)
        val adapter = ReactorHttpHandlerAdapter(httpHandler)
        return  HttpServer.create()
                .port(8080)
                .handle(adapter)
    }


}
