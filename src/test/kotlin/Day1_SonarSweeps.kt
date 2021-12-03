import org.junit.Assert
import org.junit.Test

class Day1_SonarSweeps_Tests {
    private val measurements = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)

    @Test
    fun shouldDetectIncrementingSonarSweepsOnly() {
        val increments = calculateSonarSweepIncrements(measurements)
        Assert.assertEquals(7, increments.singleIncrements)
    }

    @Test
    fun shouldDetectIncrementingWindowedSonarSweeps() {
        val increments = calculateSonarSweepIncrements(measurements)
        Assert.assertEquals(5, increments.windowedIncrements)
    }
}