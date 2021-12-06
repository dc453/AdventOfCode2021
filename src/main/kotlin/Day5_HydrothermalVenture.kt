import java.io.File

data class Line(
    val x1: Int,
    val y1: Int,
    val x2: Int,
    val y2: Int
)

class HydrothermalVents(rawLineData: String, val diagonalTrackingEnabled: Boolean = false) {
    private var highestNumber = 0
    private val lines = rawLineData.split('\n')
        .map { rawLine ->
            val line = rawLine.split(Regex(",|->"))
                .map {
                    val curNum = it.trim().toInt()
                    if (curNum > highestNumber) highestNumber = curNum
                    curNum
                }
            Line(x1 = line[0], y1 = line[1], x2 = line[2], y2 = line[3])
        }

    val grid: MutableList<MutableList<Int>> = createGrid()
    val intersectCount: Int
        get() = grid.fold(0) { totalSum, row ->
            totalSum + row.fold(0) { rowSum, col ->
                rowSum + if (col >= 2) 1 else 0
            }
        }

    init {
        trackLines()
    }

    private fun trackLines() {
        lines.forEach { line ->
            if (diagonalTrackingEnabled) {
                trackAllLines(line)
            } else {
                trackStraightLines(line)
            }
        }
    }

    private fun trackAllLines(line: Line) {
        var currentX = line.x1
        val targetX = line.x2
        var currentY = line.y1
        val targetY = line.y2

        grid[currentY][currentX]++
        while (currentX != targetX || currentY != targetY) {
            if (currentX != targetX) {
                if (currentX < targetX) currentX++ else currentX--
            }
            if (currentY != targetY) {
                if (currentY < targetY) currentY++ else currentY--
            }

            grid[currentY][currentX]++
        }
    }

    private fun trackStraightLines(line: Line) {
        if (line.y1 == line.y2) {
            val currentX = if (line.x1 < line.x2) line.x1 else line.x2
            val targetX = if (line.x1 < line.x2) line.x2 else line.x1
            for (i in currentX..targetX) {
                grid[line.y1][i]++
            }
        }
        if (line.x1 == line.x2) {
            val currentY = if (line.y1 < line.y2) line.y1 else line.y2
            val targetY = if (line.y1 < line.y2) line.y2 else line.y1
            for (i in currentY..targetY) {
                grid[i][line.x1]++
            }
        }
    }

    private fun createGrid(): MutableList<MutableList<Int>> {
        val newGrid: MutableList<MutableList<Int>> = mutableListOf()
        (0..highestNumber).map {
            val row = mutableListOf<Int>()
            (0..highestNumber).map {
                row.add(0)
            }
            newGrid.add(row)
        }
        return newGrid
    }
}

fun main() {
    val input = File("src/main/inputs/Day5_HydrothermalVenture.txt")
        .readText()

    val ventsPart1 = HydrothermalVents(input)
    println("part 1) number of overlapping lines: ${ventsPart1.intersectCount}")

    val ventsPart2 = HydrothermalVents(rawLineData = input, diagonalTrackingEnabled = true)
    println("part 2) number of overlapping lines including diagonals: ${ventsPart2.intersectCount}")
}