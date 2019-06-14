package be.fkunnen.theswisssystem

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class TheSwissSystemApplication

fun main(args: Array<String>) {
    SpringApplication.run(TheSwissSystemApplication::class.java, *args)
}
