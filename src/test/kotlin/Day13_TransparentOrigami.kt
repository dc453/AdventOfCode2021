import org.junit.Assert
import org.junit.Test

class Day13_TransparentOrigami_Tests {

    val testInput = "6,10\n" +
            "0,14\n" +
            "9,10\n" +
            "0,3\n" +
            "10,4\n" +
            "4,11\n" +
            "6,0\n" +
            "6,12\n" +
            "4,1\n" +
            "0,13\n" +
            "10,12\n" +
            "3,4\n" +
            "3,0\n" +
            "8,4\n" +
            "1,10\n" +
            "2,14\n" +
            "8,10\n" +
            "9,0\n" +
            "\n" +
            "fold along y=7\n" +
            "fold along x=5"

    @Test
    fun codeInstructions_shouldCreateAppropriatelySizedGrid() {
        val codeInstructions = CodeInstructions(testInput)
        Assert.assertEquals("rows", 15, codeInstructions.grid.size)
        Assert.assertEquals("columns", 11, codeInstructions.grid[0].size)
    }

    @Test
    fun codeInstructions_shouldMarkPointsOnGrid() {
        val codeInstructions = CodeInstructions(testInput)
        Assert.assertEquals(1, codeInstructions.grid[14][0])
        Assert.assertEquals(1, codeInstructions.grid[4][8])
    }

    @Test
    fun codeInstructions_whenFolding_shouldFoldUpForHorizontalYFolds() {
        val codeInstructions = CodeInstructions(testInput)

        codeInstructions.fold()

        Assert.assertEquals(1, codeInstructions.grid[0][0])
        Assert.assertEquals(1, codeInstructions.grid[2][6])
    }

    @Test
    fun codeInstructions_whenFolding_shouldFoldLeftForHorizontalXFolds() {
        val codeInstructions = CodeInstructions(testInput)
        val expectedGrid = mutableListOf<MutableList<Int>>(
            mutableListOf(1,1,1,1,1),
            mutableListOf(1,0,0,0,1),
            mutableListOf(1,0,0,0,1),
            mutableListOf(1,0,0,0,1),
            mutableListOf(1,1,1,1,1),
            mutableListOf(0,0,0,0,0),
            mutableListOf(0,0,0,0,0)
        )

        codeInstructions.fold()
        codeInstructions.fold()

        Assert.assertEquals(expectedGrid, codeInstructions.grid)
    }

    @Test
    fun codeInstructions_whenFolding_shouldCountVisibleDots() {
        val codeInstructions = CodeInstructions(testInput)

        codeInstructions.fold()

        Assert.assertEquals(17, codeInstructions.numPoints)
    }

    @Test
    fun codeInstructions_whenFoldingAll_shouldProcessAllFoldInstructions() {
        val codeInstructions = CodeInstructions(testInput)
        Assert.assertEquals(2, codeInstructions.foldInstructions.size)

        codeInstructions.foldAll()

        Assert.assertEquals(0, codeInstructions.foldInstructions.size)
    }
}