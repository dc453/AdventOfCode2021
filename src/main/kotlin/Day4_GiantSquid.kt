import java.io.File

data class BingoNumber(val number: Int) {
    var isMarked: Boolean = false
}

class Bingo(subsystem: String) {

    private val rawBoardData = subsystem.split("\n")
        .filter { !it.contains(',')}
    private val drawQueue: MutableList<Int> = subsystem.split("\n")[0]
        .split(",")
        .map { it.trim().toInt() }
        .toMutableList()
    private var lastDrawnNumber: Int = 0

    var boards: MutableList<BingoBoard> = mutableListOf()
    var boardCount = -1
    var winningBoard: BingoBoard? = null
    var hasWinner = false

    init {
        rawBoardDataLoop@ for (line in rawBoardData) {
            if (line == "" && boardCount < (rawBoardData.size - 1)) {
                boardCount++
                boards.add(BingoBoard())
                continue@rawBoardDataLoop
            }

            val row: List<BingoNumber> = line
                .split(" ")
                .filter { it != "" }
                .map {
                    BingoNumber(it.trim().toInt())
                }
            boards[boardCount].board.add(row)
        }
    }

    fun draw() {
        lastDrawnNumber = drawQueue.removeFirst()
        boards.forEach { currentBoard ->
            currentBoard.board.forEach { row ->
                row.forEachIndexed { colIndex, bingoNum ->
                    if (bingoNum.number == lastDrawnNumber) bingoNum.isMarked = true

                    var colCheckMarkedCount = 0
                    var rowCheckMarkedCount = 0
                    for (colCheck in row) {
                        if (colCheck.isMarked) colCheckMarkedCount++
                    }
                    for (rowCheck in currentBoard.board) {
                        if (rowCheck[colIndex].isMarked) rowCheckMarkedCount++
                    }

                    if (colCheckMarkedCount == row.size || rowCheckMarkedCount == currentBoard.board.size) {
                        declareBingo(currentBoard)
                    }
                 }
            }
        }
    }

    private fun declareBingo(board: BingoBoard) {
        board.hasBingo = true
        hasWinner = true
        winningBoard = board
    }

    inner class BingoBoard() {
        var hasBingo = false
        var board: MutableList<List<BingoNumber>> = mutableListOf()
        val bingoScore: Int
            get() = if (hasBingo) {
                var score = 0
                board.forEach { row ->
                    row.forEach { bingoNum ->
                        score += if (bingoNum.isMarked) 0 else bingoNum.number
                    }
                }
                score * lastDrawnNumber
            } else {
                0
            }
    }
}

fun main() {
    val input = File("src/main/inputs/Day4_GiantSquid.txt")
        .readText()
    val bingo = Bingo(input)
    while (!bingo.hasWinner) {
        bingo.draw()
    }
    println("winning bingo board score: ${bingo.winningBoard?.bingoScore}")
}