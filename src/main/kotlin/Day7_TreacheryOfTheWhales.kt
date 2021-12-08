import java.io.File
import kotlin.math.abs

data class CrabPosition(
    val position: Int,
    val fuelUsed: Int
)

fun getFuelEfficientPosition(input: String): CrabPosition {
    val crabPositions: List<Int> = input.split(",")
        .map { it.toInt() }
        .sorted()
    var lowestFuelUsed: Int = Int.MAX_VALUE
    var lowestFuelUsedPosition: Int = Int.MAX_VALUE

    for (curNum in 0..crabPositions[crabPositions.size - 1]) {
        val fuelUsed = crabPositions.fold(0) { sum, value ->
            sum + abs(value - curNum)
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
    val directions = getFuelEfficientPosition(input)
    println("sending crabs to position ${directions.position} for ${directions.fuelUsed} fuel")
}