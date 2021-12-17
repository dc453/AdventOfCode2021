import java.io.File

class Node(
    val x: Int,
    val y: Int,
    val risk: Int,
    var totalRisk: Int = Int.MAX_VALUE,
    var visited: Boolean = false
) {

}

class ChitonRiskAssessor(input: String) {
    val map = input.split("\n")
        .mapIndexed { rowIndex, row ->
            row.split("")
                .filter { it != "" }
                .mapIndexed { colIndex, distance ->
                    Node(colIndex, rowIndex, distance.toInt())
                }
        }
    var unvisitedNodes: MutableSet<Node> = map.flatten().toMutableSet()
    var currentNode: Node = map[0][0].also {
        it.totalRisk = 0
    }

    init {
//        println(map)
    }

    fun getSafestPathRiskLevel(): Int {
        while (unvisitedNodes.size > 0) {
            unvisitedNodes.sortedBy { it.totalRisk }
            currentNode = unvisitedNodes.first()
            checkNorth()
            checkWest()
            checkSouth()
            checkEast()
            currentNode.visited = true
            unvisitedNodes.remove(currentNode)
        }
        return map[map.lastIndex][map[0].lastIndex].totalRisk
    }

    private fun checkNorth() {
        if (currentNode.y == 0) {
            return
        }
        val targetNode = map[currentNode.y - 1][currentNode.x]
        updateTotalRisk(targetNode)
    }

    private fun checkWest() {
        if (currentNode.x == 0) {
            return
        }
        val targetNode = map[currentNode.y][currentNode.x - 1]
        updateTotalRisk(targetNode)
    }

    private fun checkSouth() {
        if (currentNode.y == map.size - 1) {
            return
        }
        val targetNode = map[currentNode.y + 1][currentNode.x]
        updateTotalRisk(targetNode)
    }

    private fun checkEast() {
        if (currentNode.x == map[0].size - 1) {
            return
        }
        val targetNode = map[currentNode.y][currentNode.x + 1]
        updateTotalRisk(targetNode)
    }

    private fun updateTotalRisk(targetNode: Node) {
        if (targetNode.visited) {
            return
        }
        val newRisk = targetNode.risk + currentNode.totalRisk
        if (newRisk < targetNode.totalRisk) {
            targetNode.totalRisk = newRisk
        }
    }
}

fun main() {
    val input = File("src/main/inputs/Day15_Chiton.txt")
        .readText()
    val chiton = ChitonRiskAssessor(input)
    val shortestPath = chiton.getSafestPathRiskLevel()
    println("part 1) the least risky path has a risk level of $shortestPath")
}