import org.junit.Assert
import org.junit.Test

class Day1Tests {
    private val measurements = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)

    @Test
    fun shouldDetectIncrementingSonarSweepsOnly() {
        val increments = calculateSonarSweepIncrements(measurements)
        Assert.assertEquals(increments.singleIncrements, 7)
    }

    @Test
    fun shouldDetectIncrementingWindowedSonarSweeps() {
        val increments = calculateSonarSweepIncrements(measurements)
        Assert.assertEquals(increments.windowedIncrements, 5)
    }
}