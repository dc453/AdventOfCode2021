import org.junit.Assert
import org.junit.Ignore
import org.junit.Test

class Day14_ExtendedPolymerization_Tests {

    val testInput = "NNCB\n" +
            "\n" +
            "CH -> B\n" +
            "HH -> N\n" +
            "CB -> H\n" +
            "NH -> C\n" +
            "HB -> C\n" +
            "HC -> B\n" +
            "HN -> C\n" +
            "NN -> C\n" +
            "BH -> H\n" +
            "NC -> B\n" +
            "NB -> B\n" +
            "BN -> B\n" +
            "BB -> N\n" +
            "BC -> B\n" +
            "CC -> N\n" +
            "CN -> C"

    @Test
    fun polymer_shouldStoreTemplate() {
        val polymer = Polymer(testInput)
        Assert.assertEquals("NNCB", polymer.template)
    }

    @Test
    fun polymer_whenOptimizing_shouldFollowPairInsertionRules() {
        val polymer = Polymer(testInput)
        polymer.optimize()
        Assert.assertEquals("NCNBCHB", polymer.template)
    }

    @Test
    fun polymer_whenOptimizing_shouldContinueToApplyInsertionRules() {
        val polymer = Polymer(testInput)
        polymer.optimize()

        polymer.optimize()
        Assert.assertEquals("NBCCNBBBCBHCB", polymer.template)

        polymer.optimize()
        Assert.assertEquals("NBBBCNCCNBBNBNBBCHBHHBCHB", polymer.template)

        polymer.optimize()
        Assert.assertEquals("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB", polymer.template)
    }

    @Test
    fun polymer_shouldCalculateDifferenceBetweenMostAndLeastCommonElement() {
        val polymer = Polymer(testInput)

        for (i in 1..10) {
            polymer.optimize()
        }

        Assert.assertEquals(1588, polymer.elementCountDifference)
    }

    @Ignore
    @Test
    fun polymer_whenCalculatingElementCounts_shouldHandleManyOptimizations() {
        val polymer = Polymer(testInput)

        for (i in 1..40) {
            polymer.optimize()
        }

        Assert.assertEquals(2188189693529, polymer.elementCountDifference)
    }
}