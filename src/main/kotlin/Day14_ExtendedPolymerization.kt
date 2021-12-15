import java.io.File

class Polymer(input: String) {
    private val pairInsertionRules: Map<String, String> = input.split("\n")
        .filter { it.contains("->") }
        .associate {
            val pair = it.split(" -> ")
            pair[0] to pair[1]
        }

    var template = input.split("\n")[0]
    var elementCountDifference = 0

    fun optimize() {
        template = template.split("")
            .zipWithNext { a, b ->
                val pair = a + b
                val insertion = pairInsertionRules.getOrDefault(pair, "")
                "$a$insertion"
            }
            .filter { it != " " }
            .joinToString("")
        determineElementCounts()
    }

    private fun determineElementCounts() {
        val elementCounts = 'A'.rangeTo('Z')
            .associate { el ->
                el.toString() to template.count { it == el }
            }
            .entries
            .filter { it.value > 0 }
            .sortedBy { it.value }
        elementCountDifference = elementCounts[elementCounts.size - 1].value - elementCounts[0].value
    }
}

fun main() {
    val input = File("src/main/inputs/Day14_ExtendedPolymerization.txt")
        .readText()
    val polymer = Polymer(input)
    for (i in 1..10) {
        polymer.optimize()
    }
    println("part 1) the difference between the most and least common elements is ${polymer.elementCountDifference}")
}