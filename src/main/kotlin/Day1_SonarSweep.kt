import java.io.File

data class SonarIncrementCounts(
    val singleIncrements: Int = 0,
    val windowedIncrements: Int = 0
)

fun main() {
    val measurements: List<Int> = File("src/main/inputs/Day1_SonarSweeps.txt")
        .readLines()
        .map {
            it.toInt()
        }

    calculateSonarSweepIncrements(measurements)
}

fun calculateSonarSweepIncrements(measurements: List<Int>): SonarIncrementCounts {
    val singleIncrementCount = determineIncrementCount(measurements)
    println("final single element increment count: $singleIncrementCount")

    val windowedMeasurements = measurements
        .windowed(3) {
            it.sum()
        }
    val windowedIncrementCount = determineIncrementCount(windowedMeasurements)
    println("final windowed elements increment count: $windowedIncrementCount")

    return SonarIncrementCounts(singleIncrementCount, windowedIncrementCount)
}

private fun determineIncrementCount(measurements: List<Int>): Int {
    return measurements
        .zipWithNext { a, b -> b > a }
        .filter { hasIncremented -> hasIncremented }
        .size
}