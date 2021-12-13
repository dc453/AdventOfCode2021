import java.io.File

data class DumboOctopus(
    var energy: Int = 0,
    var hasFlashed: Boolean = false
)

class Cavern(input: String) {
    var grid: List<MutableList<DumboOctopus>> = input.split("\n")
        .map { line ->
            line.trim().split("")
                .filter { it != "" }
                .map { DumboOctopus(it.trim().toInt()) }
                .toMutableList()
        }
    var totalFlashes: Int = 0
    var totalFlashesInCurrentStep: Int = 0
    var stepCount: Int = 0

    fun step() {
        stepCount++
        resetOctopuses()
        val nextState = getGridCopy()
        grid.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, col ->
                if (!nextState[rowIndex][colIndex].hasFlashed) {
                    nextState[rowIndex][colIndex].energy = nextState[rowIndex][colIndex].energy + 1
                }
            }
        }
        grid = nextState
        checkFlashes()
    }

    private fun getGridCopy() = grid.toMutableList()
        .map { col ->
            col
                .map {
                    it.copy()
                }
                .toMutableList()
        }

    private fun resetOctopuses() {
        grid.forEach { row ->
            row.forEach { col ->
                col.hasFlashed = false
            }
        }
        totalFlashesInCurrentStep = 0
    }

    private fun flash(row: Int, col: Int) {
        val octopus = grid[row][col]
        octopus.energy = 0
        octopus.hasFlashed = true
        totalFlashes++
        totalFlashesInCurrentStep++

        if (row > 0 && col > 0 && !grid[row - 1][col - 1].hasFlashed) grid[row - 1][col - 1].energy = grid[row - 1][col - 1].energy + 1
        if (row > 0 && !grid[row - 1][col].hasFlashed) grid[row - 1][col].energy = grid[row - 1][col].energy + 1
        if (row > 0 && col < grid[row].size - 1 && !grid[row - 1][col + 1].hasFlashed) grid[row - 1][col + 1].energy = grid[row - 1][col + 1].energy + 1
        if (col > 0 && !grid[row][col - 1].hasFlashed) grid[row][col - 1].energy = grid[row][col - 1].energy + 1
        if (col < grid[row].size - 1 && !grid[row][col + 1].hasFlashed) grid[row][col + 1].energy = grid[row][col + 1].energy + 1
        if (row < grid.size - 1 && col > 0 && !grid[row + 1][col - 1].hasFlashed) grid[row + 1][col - 1].energy = grid[row + 1][col - 1].energy + 1
        if (row < grid.size - 1 && !grid[row + 1][col].hasFlashed) grid[row + 1][col].energy = grid[row + 1][col].energy + 1
        if (row < grid.size - 1 && col < grid[row].size - 1 && !grid[row + 1][col + 1].hasFlashed) grid[row + 1][col + 1].energy = grid[row + 1][col + 1].energy + 1
    }

    private fun getAvailableFlashes(): Int {
        return grid.fold(0) { totalFlashes, row ->
            totalFlashes + row.fold(0) { rowFlashes, col ->
                rowFlashes + if (col.energy > 9) 1 else 0
            }
        }
    }

    fun getStepFlashesSync(): Int {
        while (totalFlashesInCurrentStep != grid.size * grid[0].size) {
            step()
        }
        return stepCount
    }

    private fun checkFlashes() {
        if (getAvailableFlashes() > 0) {
            grid.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { colIndex, col ->
                    if (col.energy > 9) {
                        flash(row = rowIndex, col = colIndex)
                    }
                }
            }
            checkFlashes()
        }
    }
}

fun main() {
    val input = File("src/main/inputs/Day11_DumboOctopus.txt")
        .readText()
    val cavern1 = Cavern(input)
    for (i in 1..100) cavern1.step()
    println("part 1) total number of flashes: ${cavern1.totalFlashes}")

    val cavern2 = Cavern(input)
    val step = cavern2.getStepFlashesSync()
    println("part 2) flashes sync on step $step")
}