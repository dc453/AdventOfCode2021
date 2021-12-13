import org.junit.Assert
import org.junit.Test

class Day10_DumboOctopus_Tests {

    val testInput = "5483143223\n" +
            "2745854711\n" +
            "5264556173\n" +
            "6141336146\n" +
            "6357385478\n" +
            "4167524645\n" +
            "2176841721\n" +
            "6882881134\n" +
            "4846848554\n" +
            "5283751526"

    @Test
    fun dumboOctopuses_shouldTranslateInput() {
        val cavern = Cavern(testInput)
        Assert.assertEquals(5, cavern.grid[0][0].energy)
        Assert.assertEquals(6, cavern.grid[9][9].energy)
    }

    @Test
    fun dumboOctopuses_whenStepping_shouldIncreaseEnergy() {
        val cavern = Cavern(testInput)
        cavern.step()
        Assert.assertEquals(6, cavern.grid[0][0].energy)
        Assert.assertEquals(7, cavern.grid[9][9].energy)
    }

    @Test
    fun dumboOctopuses_whenStepping_whenAbove9Energy_shouldFlash() {
        val cavern = Cavern(testInput)

        cavern.step()
        cavern.step()

        Assert.assertEquals(0, cavern.grid[0][2].energy)
    }

    @Test
    fun dumboOctopuses_whenFlashing_shouldIncreaseEnergyOfNeighbors() {
        val cavern = Cavern("222\n" +
                "292\n" +
                "222")

        cavern.step()

        Assert.assertEquals(4, cavern.grid[0][0].energy)
        Assert.assertEquals(4, cavern.grid[0][1].energy)
        Assert.assertEquals(4, cavern.grid[0][2].energy)
        Assert.assertEquals(4, cavern.grid[1][0].energy)
        Assert.assertEquals(4, cavern.grid[1][2].energy)
        Assert.assertEquals(4, cavern.grid[2][0].energy)
        Assert.assertEquals(4, cavern.grid[2][1].energy)
        Assert.assertEquals(4, cavern.grid[2][2].energy)
    }

    @Test
    fun dumboOctopuses_whenFlashing_shouldUseAllEnergyAfterFlashing() {
        val cavern = Cavern("888\n" +
                "898\n" +
                "888")

        cavern.step()

        Assert.assertEquals(0, cavern.grid[0][0].energy)
        Assert.assertEquals(0, cavern.grid[1][1].energy)
    }

    @Test
    fun dumboOctopuses_whenFlashing_shouldCountFlashes() {
        val cavern = Cavern(testInput)

        for (i in 1..10) cavern.step()
        Assert.assertEquals(204, cavern.totalFlashes)

        for (i in 11..100) cavern.step()
        Assert.assertEquals(1656, cavern.totalFlashes)
    }

    @Test
    fun dumboOctopuses_whenFlashing_shouldKeepTrackOfFlashesInStep() {
        val cavern = Cavern(testInput)

        cavern.step()
        Assert.assertEquals(0, cavern.totalFlashesInCurrentStep)

        cavern.step()
        Assert.assertEquals(35, cavern.totalFlashesInCurrentStep)

        cavern.step()
        Assert.assertEquals(45, cavern.totalFlashesInCurrentStep)
    }

    @Test
    fun dumboOctopuses_shouldReturnStepFlashesSync() {
        val cavern = Cavern("777\n" +
                "777\n" +
                "777")
        val step = cavern.getStepFlashesSync()
        Assert.assertEquals(3, step)
    }
}