import java.io.File
import kotlin.math.abs

data class CrabPosition(
    val position: Int,
    val fuelUsed: Int
)

private fun getSumOfN(n: Int): Int {
    return n * (n + 1) / 2
}

fun getFuelEfficientPosition(input: String, crabEngineering: Boolean = false): CrabPosition {
    val crabPositions: List<Int> = input.split(",")
        .map { it.toInt() }
        .sorted()
    var lowestFuelUsed: Int = Int.MAX_VALUE
    var lowestFuelUsedPosition: Int = Int.MAX_VALUE

    for (curNum in 0..crabPositions[crabPositions.size - 1]) {
        val fuelUsed = crabPositions.fold(0) { sum, value ->
            val adjustedFuel = abs(value - curNum)
            if (crabEngineering) {
                sum + getSumOfN(adjustedFuel)
            } else {
                sum + adjustedFuel
            }
        }
        if (fuelUsed < lowestFuelUsed) {
            lowestFuelUsed = fuelUsed
            lowestFuelUsedPosition = curNum
        }
    }

    return CrabPosition(position = lowestFuelUsedPosition, fuelUsed = lowestFuelUsed)
}

fun main() {
    val input = File("src/main/inputs/Day7_TreacheryOfTheWales.txt")
        .readText()
    val directions1 = getFuelEfficientPosition(input)
    println("part 1) sending crabs to position ${directions1.position} for ${directions1.fuelUsed} fuel")

    val directions2 = getFuelEfficientPosition(input = input, crabEngineering = true)
    println("part 2) sending crabs to position ${directions2.position} for ${directions2.fuelUsed} fuel")
}