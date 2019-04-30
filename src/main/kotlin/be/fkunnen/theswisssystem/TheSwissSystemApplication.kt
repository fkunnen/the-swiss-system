package be.fkunnen.theswisssystem

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TheSwissSystemApplication

fun main(args: Array<String>) {
    runApplication<TheSwissSystemApplication>(*args)
}
