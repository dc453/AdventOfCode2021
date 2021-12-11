import java.io.File

class NavigationSubsystem(input: String, val autocomplete: Boolean = false) {
    private val syntaxPairs: Map<Char, Char> = mapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}',
        '<' to '>'
    )
    private val reversedSyntaxPairs: Map<Char, Char> = syntaxPairs.entries.associate{ (k,v)-> v to k}
    private val syntaxPoints: Map<Char, Int> = mapOf(
        ')' to 3,
        ']' to 57,
        '}' to 1197,
        '>' to 25137
    )
    private val autocompleteSyntaxPoints: Map<Char, Int> = mapOf(
        '(' to 1,
        '[' to 2,
        '{' to 3,
        '<' to 4
    )

    val syntax: List<CharArray> = input.split("\n")
        .map { it.toCharArray() }
    val score: Long
        get() = if (autocomplete) {
            autocompleteScores.sorted().get(autocompleteScores.size / 2)
        } else corruptionScore
    var corruptionScore: Long = 0
    var autocompleteScores: MutableList<Long> = mutableListOf()

    init {
        syntax.forEach lineLoop@ { line ->
            val chunkQueue: MutableList<Char> = mutableListOf()
            line.forEach { char ->
                if (syntaxPairs.containsKey(char)) {
                    chunkQueue.add(char)
                }
                if (reversedSyntaxPairs.containsKey(char)) {
                    if (chunkQueue.get(chunkQueue.size - 1) == reversedSyntaxPairs[char]) {
                        chunkQueue.removeLast()
                    } else {
                        if (!autocomplete) {
                            corruptionScore += syntaxPoints[char] ?: 0
                        }
                        return@lineLoop
                    }
                }
            }
            if (autocomplete) {
                var lineScore: Long = 0
                chunkQueue.reversed().forEach { chunk ->
                    lineScore = (lineScore * 5) + (autocompleteSyntaxPoints[chunk] ?: 0)
                }
                autocompleteScores.add(lineScore)
            }
        }
    }
}

fun main() {
    val input = File("src/main/inputs/Day10_SyntaxScoring.txt")
        .readText()
    val nav1 = NavigationSubsystem(input)
    println("part 1) total syntax error score: ${nav1.score}")

    val nav2 = NavigationSubsystem(input = input, autocomplete = true)
    println("part 2) total autocomplete score: ${nav2.score}")
}