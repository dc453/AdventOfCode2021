import org.junit.Assert
import org.junit.Test

class Day4_GiantSquid_Tests {

    val testSubsystem = "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1\n" +
            "\n" +
            "22 13 17 11  0\n" +
            " 8  2 23  4 24\n" +
            "21  9 14 16  7\n" +
            " 6 10  3 18  5\n" +
            " 1 12 20 15 19\n" +
            "\n" +
            " 3 15  0  2 22\n" +
            " 9 18 13 17  5\n" +
            "19  8  7 25 23\n" +
            "20 11 10 24  4\n" +
            "14 21 16 12  6\n" +
            "\n" +
            "14 21 17 24  4\n" +
            "10 16 15  9 19\n" +
            "18  8 23 26 20\n" +
            "22 11 13  6  5\n" +
            " 2  0 12  3  7"

    @Test
    fun whenStartingBingo_shouldCreateBoards() {
        val bingo = Bingo(testSubsystem)
        Assert.assertEquals(3, bingo.boards.size)
    }

    @Test
    fun whenStartingBingo_shouldPopulateBoardsWithBingoNumbers() {
        val bingo = Bingo(testSubsystem)

        Assert.assertEquals(22, bingo.boards[0].board[0][0].number)
        Assert.assertEquals(13, bingo.boards[0].board[0][1].number)
        Assert.assertEquals(6, bingo.boards[1].board[4][4].number)
    }

    @Test
    fun whenStartingBingo_shouldNotMarkNumbers() {
        val bingo = Bingo(testSubsystem)
        Assert.assertEquals(false, bingo.boards[0].board[0][0].isMarked)
    }

    @Test
    fun whenDrawingNumbers_shouldMarkNumbers() {
        val bingo = Bingo(testSubsystem)
        bingo.draw()
        Assert.assertEquals(true, bingo.boards[1].board[2][2].isMarked)
    }

    @Test
    fun whenDrawingNumbers_shouldDrawNumbersInOrder() {
        val bingo = Bingo(testSubsystem)

        bingo.draw()
        Assert.assertEquals(false, bingo.boards[0].board[1][3].isMarked)

        bingo.draw()
        Assert.assertEquals(true, bingo.boards[0].board[1][3].isMarked)
    }

    @Test
    fun whenDrawingNumbers_shouldCheckForBingoHorizontally() {
        val bingo = Bingo("22,13,17,11,0\n" +
                "\n" +
                "22 13 17 11  0\n" +
                " 8  2 23  4 24\n" +
                "21  9 14 16  7\n" +
                " 6 10  3 18  5\n" +
                " 1 12 20 15 19\n")
        Assert.assertEquals(false, bingo.boards[0].hasBingo)

        bingo.draw()
        bingo.draw()
        bingo.draw()
        bingo.draw()
        bingo.draw()

        Assert.assertEquals(true, bingo.boards[0].hasBingo)
    }

    @Test
    fun whenDrawingNumbers_shouldCheckForBingoVertically() {
        val bingo = Bingo("22,8,21,6,1\n" +
                "\n" +
                "22 13 17 11  0\n" +
                " 8  2 23  4 24\n" +
                "21  9 14 16  7\n" +
                " 6 10  3 18  5\n" +
                " 1 12 20 15 19\n")
        Assert.assertEquals(false, bingo.boards[0].hasBingo)

        bingo.draw()
        bingo.draw()
        bingo.draw()
        bingo.draw()
        bingo.draw()

        Assert.assertEquals(true, bingo.boards[0].hasBingo)
    }

    @Test
    fun whenDrawingNumbers_whenHasBingo_shouldCalculateScore() {
        val bingo = Bingo(testSubsystem)

        for (i in 1..12) {
            bingo.draw()
        }

        Assert.assertEquals(4512, bingo.boards[2].bingoScore)
    }

    @Test
    fun whenDrawingNumbers_whenHasBingo_shouldReportWinner() {
        val bingo = Bingo(testSubsystem)

        for (i in 1..12) {
            bingo.draw()
        }

        Assert.assertEquals(true, bingo.hasWinner)
    }

    @Test
    fun whenDrawingNumbers_whenHasBingo_shouldReportWinningBoard() {
        val bingo = Bingo(testSubsystem)

        for (i in 1..12) {
            bingo.draw()
        }

        Assert.assertEquals(bingo.boards[2], bingo.winningBoard)
    }

    @Test
    fun whenDrawingNumbers_whenHasBingo_shouldKeepRecordOfNumberOfWinningBoards() {
        val bingo = Bingo(testSubsystem)

        for (i in 1..12) {
            bingo.draw()
        }
        bingo.boards.forEach {
            println("winner ${it.hasBingo}")
            println("board ${it.board}")
        }

        Assert.assertEquals(1, bingo.numWinningBoards)
    }
}