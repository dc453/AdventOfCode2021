import java.io.File

data class Point(
    val x: Int,
    val y: Int
)

data class FoldInstruction(
    val isHorizontal: Boolean,
    val line: Int
)

class CodeInstructions(input: String) {
    private val coordinates: List<Point> = input.split("\n")
        .filter { it != "" && !it.contains("fold") }
        .map {
            val parts = it.split(",")
            Point(x = parts[0].toInt(), y = parts[1].toInt())
        }
    val foldInstructions: MutableList<FoldInstruction> = input.split("\n")
        .filter { it.contains("fold") }
        .map {
            val parts = it.split("=")
            val isHorizontal = parts[0].last() == 'y'
            FoldInstruction(isHorizontal, parts[1].toInt())
        }
        .toMutableList()

    private val rowSize = (foldInstructions.filter { it.isHorizontal }.maxByOrNull { it.line }!!.line * 2) + 1
    private val colSize = (foldInstructions.filter { !it.isHorizontal }.maxByOrNull { it.line }!!.line * 2) + 1
    var grid: MutableList<MutableList<Int>> = MutableList(rowSize) {
        MutableList(colSize) { 0 }
    }
    val numPoints: Int
        get() = grid.fold(0) { totalPoints, row ->
            totalPoints + row.fold(0) { rowPoints, col ->
                rowPoints + if (col > 0) 1 else 0
            }
        }

    init {
        coordinates.forEach { coord ->
            grid[coord.y][coord.x] = 1
        }
    }

    fun printGrid() {
        grid.forEach { row ->
            println(row.joinToString(" ") { if (it == 1) "#" else " " })
        }
    }

    fun fold() {
        val instruction = foldInstructions.removeFirst()
        val gridPart1 = if (instruction.isHorizontal) {
            grid.take(instruction.line)
        } else {
            grid.map { row -> row.take(instruction.line) }
        }
        val gridPart2 = if (instruction.isHorizontal) {
            grid.takeLast(instruction.line).reversed()
        } else {
            grid.map { row -> row.takeLast(instruction.line).reversed() }
        }
        val foldedGrid = gridPart1
            .mapIndexed { rowIndex, row ->
                MutableList(row.size) { colIndex ->
                    if (gridPart1[rowIndex][colIndex] == 1 || gridPart2[rowIndex][colIndex] == 1) 1 else 0
                }
            }
            .toMutableList()
        grid = foldedGrid
    }

    fun foldAll() {
        while (foldInstructions.size > 0) {
            fold()
        }
    }
}

fun main() {
    val input = File("src/main/inputs/Day13_TransparentOrigami.txt")
        .readText()
    val codeInstructions = CodeInstructions(input)
    codeInstructions.fold()
    println("part 1) total number of dots visible after first fold: ${codeInstructions.numPoints}")

    codeInstructions.foldAll()
    println("part 2) code is:")
    codeInstructions.printGrid()
}