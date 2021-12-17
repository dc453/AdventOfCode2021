import java.io.File

class Node(
    val x: Int,
    val y: Int,
    val risk: Int,
    var totalRisk: Int = Int.MAX_VALUE,
    var visited: Boolean = false
)

class ChitonRiskAssessor(input: String) {
    val map: MutableList<MutableList<Node>> = input.split("\n")
        .mapIndexed { rowIndex, row ->
            row.split("")
                .filter { it != "" }
                .mapIndexed { colIndex, distance ->
                    Node(colIndex, rowIndex, distance.toInt())
                }
                .toMutableList()
        }
        .toMutableList()
    var fullMap: MutableList<MutableList<Node>> = buildFullMap()
    var unvisitedNodes: MutableSet<Node> = map.flatten().toMutableSet()
    var usingFullMap = false
    val targetMap: MutableList<MutableList<Node>>
        get() = if (usingFullMap) fullMap else map
    var currentNode: Node = targetMap[0][0].also {
        it.totalRisk = 0
    }

    private fun buildFullMap(): MutableList<MutableList<Node>> {
        val tempMap: MutableList<MutableList<Node?>> = MutableList(map.size * 5) {
            MutableList(map[0].size * 5) { null }
        }
        for (x in 0..4) {
            for (y in 0..4) {
                map.forEachIndexed { rowIndex, nodes ->
                    nodes.forEachIndexed { colIndex, node ->
                        var risk = (node.risk + x + y)
                        if (risk > 9) {
                            risk %= 9
                        }
                        tempMap[rowIndex + (y * map.size)][colIndex + (x * map[0].size)] = Node(
                            colIndex + (x * map[0].size),
                            rowIndex + (y * map[0].size),
                            risk
                        )
                    }
                }
            }
        }
        return tempMap as MutableList<MutableList<Node>>
    }

    fun getSafestPathRiskLevel(useFullMap: Boolean = false): Int {
        usingFullMap = useFullMap
        if (useFullMap) {
            unvisitedNodes = fullMap.flatten().toMutableSet()
        }
        targetMap[0][0].totalRisk = 0

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

//        printTotalRiskLevels()

        return targetMap[targetMap.lastIndex][targetMap[0].lastIndex].totalRisk
    }

    private fun printTotalRiskLevels() {
        targetMap.forEach { row ->
            println(row.joinToString("") { "${it.totalRisk} " })
        }
    }

    private fun checkNorth() {
        if (currentNode.y == 0) {
            return
        }
        val targetNode = targetMap[currentNode.y - 1][currentNode.x]
        updateTotalRisk(targetNode)
    }

    private fun checkWest() {
        if (currentNode.x == 0) {
            return
        }
        val targetNode = targetMap[currentNode.y][currentNode.x - 1]
        updateTotalRisk(targetNode)
    }

    private fun checkSouth() {
        if (currentNode.y == targetMap.size - 1) {
            return
        }
        val targetNode = targetMap[currentNode.y + 1][currentNode.x]
        updateTotalRisk(targetNode)
    }

    private fun checkEast() {
        if (currentNode.x == targetMap[0].size - 1) {
            return
        }
        val targetNode = targetMap[currentNode.y][currentNode.x + 1]
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
    val path1 = chiton.getSafestPathRiskLevel()
    println("part 1) the least risky path has a risk level of $path1")

    // TODO - figure out what is causing this total risk value to be incorrect
    val path2 = chiton.getSafestPathRiskLevel(useFullMap = true)
    println("part 2) the least risky path for the full map has a risk level of $path2")
}