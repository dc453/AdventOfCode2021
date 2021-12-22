class ImageEnhancer(input: String) {

    private val pixelConversion: Map<Char, Char> = mapOf('#' to '1', '.' to '0')
    val enhancementAlgorithm = input.split("\n\n")[0]
    var image: List<List<String>> = input.split("\n\n")[1]
        .split("\n")
        .map { row ->
            row.split("")
                .filter { it != "" }
        }
    private var imageCopy: MutableList<MutableList<String>> = mutableListOf(mutableListOf())

    init {
        println("image:")
        image.forEach { println(it.joinToString("")) }

        createImageCopy()
    }

    private fun createImageCopy() {
        val colSize = image[0].size
        imageCopy = ArrayList(image)
            .map { row ->
                val paddedRow = row.toMutableList()
                paddedRow.add(0, ".")
                paddedRow.add(paddedRow.lastIndex + 1, ".")
                paddedRow
            }
            .toMutableList()
        imageCopy.add(0, MutableList(colSize + 2) { "." })
        imageCopy.add(imageCopy.lastIndex + 1, MutableList(colSize + 2) { "." })
    }

    fun getLookupIndexFromPoint(x: Int, y: Int): Int {
        val pixels: String = "${imageCopy[y][x]}${imageCopy[y][x + 1]}${imageCopy[y][x + 2]}" +
                "${imageCopy[y + 1][x]}${imageCopy[y + 1][x + 1]}${imageCopy[y + 1][x + 2]}" +
                "${imageCopy[y + 2][x]}${imageCopy[y + 2][x + 1]}${imageCopy[y + 2][x + 2]}"
        val lookupIndex = Integer.parseInt(
            pixels.map { pixelConversion[it] }
                .joinToString(""),
            2)
//        println("pixels for ${x}x${y} = $pixels = $lookupIndex = ${enhancementAlgorithm[lookupIndex]}")
        return lookupIndex
    }

    fun enhance() {
        val enhancedImage: MutableList<MutableList<String>> =
            MutableList(imageCopy.size) {
                MutableList(imageCopy[0].size) { "." }
            }
        image.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, _ ->
                val lookupIndex = getLookupIndexFromPoint(colIndex, rowIndex)
                enhancedImage[rowIndex + 1][colIndex + 1] = enhancementAlgorithm[lookupIndex].toString()
            }
        }
        println("\nenhancedImage")
        println(enhancedImage.joinToString("\n") { it.joinToString("") })
        image = enhancedImage
        createImageCopy()

//        imageCopy.forEach { println(it.joinToString("")) }
    }
}

fun main() {

    println("part1)")
}