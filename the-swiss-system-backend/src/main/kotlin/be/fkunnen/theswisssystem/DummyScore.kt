package be.fkunnen.theswisssystem

data class DummyScore constructor(var numberOfWins: Int = 0, var numberOfLosses: Int = 0): Score {

    fun increaseWins() {
        numberOfWins++
    }

    fun increaseLosses() {
        numberOfLosses++
    }
}
