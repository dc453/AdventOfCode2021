import org.junit.Assert
import org.junit.Test

class Day15_Chiton_Tests {

    val testInput = "1163751742\n" +
            "1381373672\n" +
            "2136511328\n" +
            "3694931569\n" +
            "7463417111\n" +
            "1319128137\n" +
            "1359912421\n" +
            "3125421639\n" +
            "1293138521\n" +
            "2311944581"


    @Test
    fun chitonRiskAssessor_shouldDetermineLeastRiskyPath() {
        val chitonRiskAssessor = ChitonRiskAssessor("12\n" +
                "61")
        val path = chitonRiskAssessor.getSafestPathRiskLevel()
        Assert.assertEquals(3, path)
    }

    @Test
    fun chitonRiskAssessor_shouldDetermineLeastRiskyPath_whenMultipleBranchingPaths() {
        val chitonRiskAssessor = ChitonRiskAssessor(testInput)
        val path = chitonRiskAssessor.getSafestPathRiskLevel()
        Assert.assertEquals(40, path)
    }
}