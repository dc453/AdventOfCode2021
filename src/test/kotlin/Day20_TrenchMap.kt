import org.junit.Assert
import org.junit.Test

class Day20_TrenchMap_Tests {

    val testInput = "..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..##" +
            "#..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###" +
            ".######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#." +
            ".#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#....." +
            ".#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#.." +
            "...####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#....." +
            "..##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#\n" +
            "\n" +
            "#..#.\n" +
            "#....\n" +
            "##..#\n" +
            "..#..\n" +
            "..###"

    @Test
    fun imageEnhancer_shouldStoreEnhancementAlgorithm() {
        val imageEnhancer = ImageEnhancer(testInput)
        Assert.assertEquals(
            "..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..###..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###.######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#..#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#......#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#.....####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#.......##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#",
            imageEnhancer.enhancementAlgorithm
        )
    }

    @Test
    fun imageEnhancer_shouldStoreInputImage() {
        val imageEnhancer = ImageEnhancer(testInput)
        Assert.assertEquals("#", imageEnhancer.image[0][0])
        Assert.assertEquals(".", imageEnhancer.image[0][1])
        Assert.assertEquals("#", imageEnhancer.image[4][4])
    }

    @Test
    fun imageEnhancer_shouldGetAlgorithmIndexFromImagePoint() {
        val imageEnhancer = ImageEnhancer(testInput)
        val lookupIndex = imageEnhancer.getLookupIndexFromPoint(x = 2, y = 2)
        Assert.assertEquals(34, lookupIndex)
    }

    // TODO: fix issue with wrong pixels being displayed
    @Test
    fun imageEnhancer_whenEnhancing_shouldUseEnhancementAlgorithm() {
        val imageEnhancer = ImageEnhancer(testInput)
        imageEnhancer.enhance()
        Assert.assertEquals(
            ".##.##.\n" +
                    "#..#.#.\n" +
                    "##.#..#\n" +
                    "####..#\n" +
                    ".#..##.\n" +
                    "..##..#\n" +
                    "...#.#.",
            imageEnhancer.image.joinToString("\n") { it.joinToString("") }
        )
    }
}