import org.junit.Assert
import org.junit.Test

class Day7_TreacheryOfTheWhales_Tests {

    @Test
    fun whenDeterminingMostFuelEfficientMove_shouldReportPosition() {
        val testInput = "16,1,2,0,4,2,7,1,2,14"
        val directions = getFuelEfficientPosition(testInput)
        Assert.assertEquals(2, directions.position)
    }

    @Test
    fun whenDeterminingMostFuelEfficientMove_shouldReportFuelUsed() {
        val testInput = "16,1,2,0,4,2,7,1,2,14"
        val directions = getFuelEfficientPosition(testInput)
        Assert.assertEquals(37, directions.fuelUsed)
    }

    @Test
    fun whenDeterminingMostFuelEfficientMove_andCrabEngineeringEnabled_shouldReportPosition() {
        val testInput = "16,1,2,0,4,2,7,1,2,14"
        val directions = getFuelEfficientPosition(input = testInput, crabEngineering = true)
        Assert.assertEquals(5, directions.position)
    }

    @Test
    fun whenDeterminingMostFuelEfficientMove_andCrabEngineeringEnabled_shouldReportFuelUsed() {
        val testInput = "16,1,2,0,4,2,7,1,2,14"
        val directions = getFuelEfficientPosition(input = testInput, crabEngineering = true)
        Assert.assertEquals(168, directions.fuelUsed)
    }
}