import org.junit.Assert
import org.junit.Test

class Day10_SyntaxScoring_Tests {

    private val testInput = "[({(<(())[]>[[{[]{<()<>>\n" +
            "[(()[<>])]({[<{<<[]>>(\n" +
            "{([(<{}[<>[]}>{[]{[(<()>\n" +
            "(((({<>}<{<{<>}{[]{[]{}\n" +
            "[[<[([]))<([[{}[[()]]]\n" +
            "[{[{({}]{}}([{[{{{}}([]\n" +
            "{<[[]]>}<{[{[{[]{()[[[]\n" +
            "[<(<(<(<{}))><([]([]()\n" +
            "<{([([[(<>()){}]>(<<{{\n" +
            "<{([{{}}[<[[[<>{}]]]>[]]"

    @Test
    fun navigationSubsystem_shouldProcessInput() {
        val nav = NavigationSubsystem(testInput)
        Assert.assertEquals(10, nav.syntax.size)
    }

    @Test
    fun navigationSubsystem_shouldScoreIllegalParens() {
        val nav = NavigationSubsystem("[<(<(<(<{}))><([]([]()")
        Assert.assertEquals(3, nav.score)
    }

    @Test
    fun navigationSubsystem_shouldScoreIllegalSquareBrackets() {
        val nav = NavigationSubsystem("[{[{({}]{}}([{[{{{}}([]")
        Assert.assertEquals(57, nav.score)
    }

    @Test
    fun navigationSubsystem_shouldScoreIllegalCurlyBrackets() {
        val nav = NavigationSubsystem("{([(<{}[<>[]}>{[]{[(<()>")
        Assert.assertEquals(1197, nav.score)
    }

    @Test
    fun navigationSubsystem_shouldScoreIllegalAngledBrackets() {
        val nav = NavigationSubsystem("<{([([[(<>()){}]>(<<{{")
        Assert.assertEquals(25137, nav.score)
    }

    @Test
    fun navigationSubsystem_shouldTotalUpScore() {
        val nav = NavigationSubsystem(testInput)
        Assert.assertEquals(26397, nav.score)
    }

    @Test
    fun navigationSubsystem_whenAutocompleteEnabled_shouldCompleteAndScoreParens() {
        val nav = NavigationSubsystem(input = "(([])(", autocomplete = true)
        Assert.assertEquals(6, nav.score)
    }

    @Test
    fun navigationSubsystem_whenAutocompleteEnabled_shouldCompleteAndScoreSquareBrackets() {
        val nav = NavigationSubsystem(input = "[([])[", autocomplete = true)
        Assert.assertEquals(12, nav.score)
    }

    @Test
    fun navigationSubsystem_whenAutocompleteEnabled_shouldCompleteAndScoreCurlyBrackets() {
        val nav = NavigationSubsystem(input = "{([]){", autocomplete = true)
        Assert.assertEquals(18, nav.score)
    }

    @Test
    fun navigationSubsystem_whenAutocompleteEnabled_shouldCompleteAndScoreAngledBrackets() {
        val nav = NavigationSubsystem(input = "<([])<", autocomplete = true)
        Assert.assertEquals(24, nav.score)
    }

    @Test
    fun navigationSubsystem_whenAutocompleteEnabled_shouldReportMiddleScore() {
        val nav = NavigationSubsystem(input = testInput, autocomplete = true)
        Assert.assertEquals(288957, nav.score)
    }
}