import java.io.File

class SevenSegmentDisplay(input: String) {
    val segmentCountOfNumber: Map<Int, Int> = mapOf(
        1 to 2,
        4 to 4,
        7 to 3,
        8 to 7
    )
    val reversedSegmentCountOfNumber = segmentCountOfNumber.entries.associate{(k,v)-> v to k}
    val displays: List<List<Char>> = listOf(
        listOf('a','b','c','e','f','g'),
        listOf('c','f'),
        listOf('a','c','d','e','g'),
        listOf('a','c','d','f','g'),
        listOf('b','c','d','f'),
        listOf('a','b','d','f','g'),
        listOf('a','b','d','e','f','g'),
        listOf('a','c','f'),
        listOf('a','b','c','d','e','f','g'),
        listOf('a','b','c','d','f','g')
    )
    val entries: List<NoteEntry> = input.split("\n")
        .map { entry ->
            val entryParts = entry
                .split("|")
                .map { part ->
                    part.trim().split(" ")
                }
            NoteEntry(signalPatterns = entryParts[0], output = entryParts[1])
        }

    fun getNumOf(num: Int): Int {
        val segmentMatch = segmentCountOfNumber.getOrElse(num) { null }
        return entries.fold(0) { totalSum, entry ->
            totalSum + entry.output.fold(0) { entrySum, num ->
                entrySum + if (num.length == segmentMatch) 1 else 0
            }
        }
    }

    fun getNumOfUnique(): Int {
        return listOf(1, 4, 7, 8).fold(0) { sum, num ->
            sum + getNumOf(num)
        }
    }

    fun unscramble() {

    }


    inner class NoteEntry(
        val signalPatterns: List<String>,
        val output: List<String>
    ) {
        val translation = mutableMapOf<Char, Char?>().also { trans ->
            listOf('a', 'b', 'c', 'd', 'e', 'f', 'g').forEach {
                trans[it] = null
            }
        }

        init {
            signalPatterns.forEach {
                val segments = it.toCharArray()
//                println("segments $segments ${segments.size}")
                if (reversedSegmentCountOfNumber.containsKey(segments.size)) {
                    val display: Int? = reversedSegmentCountOfNumber[segments.size]
                    segments.forEachIndexed { index, segment ->
                        val correctSegment = displays[display!!][index]
                        println("correctSegment $correctSegment, segment $segment")
                        // TODO need to sort through possibilities by comparing against other signals
                        //  instead of assuming the translation is correct
                        translation[correctSegment] = segment
                    }
                }
            }
        }
    }
}

fun main() {
    val input = File("src/main/inputs/Day8_SevenSegmentSearch.txt")
        .readText()
    val display = SevenSegmentDisplay(input)

    println("part 1) the digits 1, 4, 7, or 8 appear ${display.getNumOfUnique()} times")
}