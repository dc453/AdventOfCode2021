import java.io.File

class DiracDiceGame(input: String) {
    private val dice = DiracDice()
    private val hasWinner: Boolean
        get() = players.any { it.score >= 1000 }
    val gameScore: Int
        get() = if (!hasWinner) {
            0
        } else {
            players.minByOrNull { it.score }!!.score * dice.rollCount
        }

    val players = input.split("\n")
        .map {
            val startingPosition = it.split(": ")[1].toInt()
            Player(startingPosition, dice)
        }

    fun advanceTurn() {
        players.forEach {
            if (hasWinner) {
                return
            }
            it.takeTurn()
        }
    }

    fun advanceTurnsUntilWin() {
        while (!hasWinner) {
            advanceTurn()
        }
    }
}


class Player(
    var position: Int = 0,
    private val dice: DiracDice
) {
    var score: Int = 0

    fun move() {
        val spaces = dice.roll()
        position += spaces
        if (position > 10) {
            position %= 10
        }
        if (position == 0) {
            position = 10
        }
    }

    fun takeTurn() {
        for (i in 1..3) {
            move()
        }
        score += position
    }
}


class DiracDice() {
    private var rollValue: Int = 0
    var rollCount = 0

    fun roll(): Int {
        rollCount++

        rollValue++
        if (rollValue > 100) {
            rollValue = 1
        }

        return rollValue
    }
}


fun main() {
    val input = File("src/main/inputs/Day21_DiracDice.txt")
        .readText()
    val game = DiracDiceGame(input)
    game.advanceTurnsUntilWin()
    println("part 1) the score is ${game.gameScore}")
}