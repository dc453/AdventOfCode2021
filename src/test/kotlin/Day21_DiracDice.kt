import org.junit.Assert
import org.junit.Test

class Day21_DiracDice_Tests {

    val testInput = "Player 1 starting position: 4\n" +
            "Player 2 starting position: 8"

    @Test
    fun diracDiceGame_shouldStartPlayersAtSpecifiedStartingPositions() {
        val game = DiracDiceGame(testInput)
        Assert.assertEquals(4, game.players[0].position)
        Assert.assertEquals(8, game.players[1].position)
    }

    @Test
    fun diracDiceGame_dice_shouldRoll1To100() {
        val dice = DiracDice()

        var roll = dice.roll()
        Assert.assertEquals("initial roll", 1, roll)

        for (i in 1..99) {
            roll = dice.roll()
        }
        Assert.assertEquals("100th roll", 100, roll)
    }

    @Test
    fun diracDiceGame_dice_shouldResetRollAfter100() {
        val dice = DiracDice()

        for (i in 1..100) {
            dice.roll()
        }

        Assert.assertEquals(1, dice.roll())
    }

    @Test
    fun diracDiceGame_dice_shouldKeepTrackOfRolls() {
        val dice = DiracDice()

        for (i in 1..100) {
            dice.roll()
        }

        Assert.assertEquals(100, dice.rollCount)
    }

    @Test
    fun diracDiceGame_players_shouldStartWithAScoreOfZero() {
        val game = DiracDiceGame(testInput)
        Assert.assertEquals(0, game.players[0].score)
    }

    @Test
    fun diracDiceGame_players_whenMoving_shouldAddDiceRollToPosition() {
        val game = DiracDiceGame(testInput)

        game.players[0].move()
        Assert.assertEquals(5, game.players[0].position)

        game.players[0].move()
        Assert.assertEquals(7, game.players[0].position)
    }

    @Test
    fun diracDiceGame_players_whenMoving_shouldReturnTo1After10() {
        val game = DiracDiceGame("Player 1 starting position: 10\n" +
                "Player 2 starting position: 8")
        game.players[0].move()
        Assert.assertEquals(1, game.players[0].position)
    }

    @Test
    fun diracDiceGame_players_whenTakingTurn_shouldMove3Times() {
        val game = DiracDiceGame(testInput)
        game.players[0].takeTurn()
        Assert.assertEquals(10, game.players[0].position)
    }

    @Test
    fun diracDiceGame_players_whenTakingTurn_shouldAddFinalPositionToScore() {
        val game = DiracDiceGame(testInput)
        game.players[0].takeTurn()
        Assert.assertEquals(10, game.players[0].score)
    }

    @Test
    fun diracDiceGame_players_shouldUseSameDice() {
        val game = DiracDiceGame(testInput)
        game.players[0].takeTurn()
        game.players[1].takeTurn()
        Assert.assertEquals(3, game.players[1].score)
    }

    @Test
    fun diracDiceGame_whenAdvancingTurn_bothPlayersShouldTakeTurns() {
        val game = DiracDiceGame(testInput)
        game.advanceTurn()
        Assert.assertEquals(10, game.players[0].score)
        Assert.assertEquals(3, game.players[1].score)
    }

    @Test
    fun diracDiceGame_shouldEndGameWhenAPlayerReachesAScoreOf1000() {
        val game = DiracDiceGame(testInput)
        game.advanceTurnsUntilWin()
        Assert.assertEquals(1000, game.players[0].score)
    }

    @Test
    fun diracDiceGame_shouldStopOtherPlayersFromTakingTurnsWhenGameIsOver() {
        val game = DiracDiceGame(testInput)
        game.advanceTurnsUntilWin()
        Assert.assertEquals(745, game.players[1].score)
    }

    @Test
    fun diracDiceGame_whenScoring_multiplyScoreOfLosingPlayerByNumDiceRolls() {
        val game = DiracDiceGame(testInput)
        game.advanceTurnsUntilWin()
        Assert.assertEquals(739785, game.gameScore)
    }
}