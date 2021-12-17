import java.io.File

class Node(
    val x: Int,
    val y: Int,
    val distance: Int,
    var totalDistance: Int = Int.MAX_VALUE,
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
        it.totalDistance = 0
    }

    init {
//        println(map)
    }

    fun getShortestPath(): Int {
        while (unvisitedNodes.size > 0) {
            unvisitedNodes.sortedBy { it.totalDistance }
            currentNode = unvisitedNodes.first()
            checkNorth()
            checkWest()
            checkSouth()
            checkEast()
            currentNode.visited = true
            unvisitedNodes.remove(currentNode)
        }
        return map[map.lastIndex][map[0].lastIndex].totalDistance
    }

    private fun checkNorth() {
        if (currentNode.y == 0) {
            return
        }
        val targetNode = map[currentNode.y - 1][currentNode.x]
        updateTotalDistance(targetNode)
    }

    private fun checkWest() {
        if (currentNode.x == 0) {
            return
        }
        val targetNode = map[currentNode.y][currentNode.x - 1]
        updateTotalDistance(targetNode)
    }

    private fun checkSouth() {
        if (currentNode.y == map.size - 1) {
            return
        }
        val targetNode = map[currentNode.y + 1][currentNode.x]
        updateTotalDistance(targetNode)
    }

    private fun checkEast() {
        if (currentNode.x == map[0].size - 1) {
            return
        }
        val targetNode = map[currentNode.y][currentNode.x + 1]
        updateTotalDistance(targetNode)
    }

    private fun updateTotalDistance(targetNode: Node) {
        if (targetNode.visited) {
            return
        }
        val newDistance = targetNode.distance + currentNode.totalDistance
        if (newDistance < targetNode.totalDistance) {
            targetNode.totalDistance = newDistance
        }
    }
}

fun main() {
    val input = File("src/main/inputs/Day15_Chiton.txt")
        .readText()
    val chiton = ChitonRiskAssessor(input)
    val shortestPath = chiton.getShortestPath()
    println("part 1) the shortest path is $shortestPath")
}