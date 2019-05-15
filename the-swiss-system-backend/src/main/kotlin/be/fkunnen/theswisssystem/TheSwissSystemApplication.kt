package be.fkunnen.theswisssystem

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TheSwissSystemApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<TheSwissSystemApplication>(*args)
        }
    }
}
