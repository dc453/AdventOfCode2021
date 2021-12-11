import org.junit.Assert
import org.junit.Test

class Day8_SevenSegmentSearch_Tests {

    val testInput = "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe\n" +
            "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc\n" +
            "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg\n" +
            "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb\n" +
            "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea\n" +
            "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb\n" +
            "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe\n" +
            "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef\n" +
            "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb\n" +
            "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce"

    @Test
    fun sevenSegmentDisplay_shouldDetectNumberOf1s() {
        val display = SevenSegmentDisplay(testInput)
        val num1s = display.getNumOf(1)
        Assert.assertEquals(8, num1s)
    }

    @Test
    fun sevenSegmentDisplay_shouldDetectNumberOf4s() {
        val display = SevenSegmentDisplay(testInput)
        val num4s = display.getNumOf(4)
        Assert.assertEquals(6, num4s)
    }

    @Test
    fun sevenSegmentDisplay_shouldDetectNumberOf7s() {
        val display = SevenSegmentDisplay(testInput)
        val num7s = display.getNumOf(7)
        Assert.assertEquals(5, num7s)
    }

    @Test
    fun sevenSegmentDisplay_shouldDetectNumberOf8s() {
        val display = SevenSegmentDisplay(testInput)
        val num8s = display.getNumOf(8)
        Assert.assertEquals(7, num8s)
    }

    @Test
    fun sevenSegmentDisplay_shouldCountUniqueNumbers() {
        val display = SevenSegmentDisplay(testInput)
        val numUnique = display.getNumOfUnique()
        Assert.assertEquals(26, numUnique)
    }

    @Test
    fun sevenSegmentDisplay_whenUnscrambling_shouldTranslate1s() {
        val display = SevenSegmentDisplay(testInput)
        val noteEntryUnderTest = display.entries[0]
        Assert.assertEquals('b', noteEntryUnderTest.translation['c'])
        Assert.assertEquals('e', noteEntryUnderTest.translation['f'])
    }

    @Test
    fun sevenSegmentDisplay_whenUnscrambling_shouldTranslate4s() {
        val display = SevenSegmentDisplay(testInput)
        val noteEntryUnderTest = display.entries[0]
        Assert.assertEquals('c', noteEntryUnderTest.translation['b'])
        Assert.assertEquals('g', noteEntryUnderTest.translation['c'])
        Assert.assertEquals('e', noteEntryUnderTest.translation['d'])
        Assert.assertEquals('b', noteEntryUnderTest.translation['f'])
    }

}