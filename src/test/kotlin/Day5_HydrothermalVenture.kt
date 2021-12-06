import org.junit.Assert
import org.junit.Test

class Day5_HydrothermalVenture_Tests {

    val testInput = "0,9 -> 5,9\n" +
            "8,0 -> 0,8\n" +
            "9,4 -> 3,4\n" +
            "2,2 -> 2,1\n" +
            "7,0 -> 7,4\n" +
            "6,4 -> 2,0\n" +
            "0,9 -> 2,9\n" +
            "3,4 -> 1,4\n" +
            "0,0 -> 8,8\n" +
            "5,5 -> 8,2"


    @Test
    fun whenMappingHydrothermalVents_shouldCreateGrid() {
        val vents = HydrothermalVents(testInput)
        Assert.assertEquals(10, vents.grid.size)
        Assert.assertEquals(10, vents.grid[0].size)
    }

    @Test
    fun whenMappingHydrothermalVents_shouldTrackLinesHorizontally() {
        val vents = HydrothermalVents(testInput)
        Assert.assertEquals(2, vents.grid[9][0])
        Assert.assertEquals(2, vents.grid[9][1])
    }

    @Test
    fun whenMappingHydrothermalVents_shouldTrackLinesVertically() {
        val vents = HydrothermalVents(testInput)
        Assert.assertEquals(1, vents.grid[1][2])
        Assert.assertEquals(1, vents.grid[2][2])
    }

    @Test
    fun whenMappingHydrothermalVents_shouldCalculateIntersectingLines() {
        val vents = HydrothermalVents(testInput)
        Assert.assertEquals(5, vents.intersectCount)
    }

    @Test
    fun whenMappingHydrothermalVents_shouldNotTrackLinesDiagonally() {
        val vents = HydrothermalVents(testInput)
        Assert.assertEquals(0, vents.grid[0][8])
        Assert.assertEquals(1, vents.grid[1][7])
    }

    @Test
    fun whenMappingHydrothermalVents_whenDiagonalTrackingEnabled_shouldTrackLinesDiagonally() {
        val vents = HydrothermalVents(rawLineData = testInput, diagonalTrackingEnabled = true)
        Assert.assertEquals(1, vents.grid[0][8])
        Assert.assertEquals(2, vents.grid[1][7])
    }
}