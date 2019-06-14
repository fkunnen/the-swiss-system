package be.fkunnen.theswisssystem

class Player constructor(val name: String, var score: DummyScore = DummyScore()) {

    fun increaseWins() {
        score.increaseWins()
    }

    fun increaseLosses() {
        score.increaseLosses()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun toString(): String {
        return "Player(name='$name', score=$score)"
    }
}
