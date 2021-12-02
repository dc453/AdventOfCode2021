import org.junit.Assert
import org.junit.Test

class Day2_Dive_Tests {

    @Test
    fun whenMovingForward_shouldOnlyIncrementHorizontalPosition() {
        val submarine = Submarine()
        submarine.aim = 3

        submarine.move("forward", 5)

        Assert.assertEquals(submarine.horizontalPosition, 5)
        Assert.assertEquals(submarine.depth, 0)
    }

    @Test
    fun whenMovingForward_whenAimEnabled_shouldAlsoIncreaseDepth() {
        val submarine = Submarine(true)
        submarine.aim = 3

        submarine.move("forward", 5)

        Assert.assertEquals(submarine.horizontalPosition, 5)
        Assert.assertEquals(submarine.depth, 15)
    }

    @Test
    fun whenMovingDown_shouldOnlyIncreaseDepth() {
        val submarine = Submarine()

        submarine.move("down", 6)

        Assert.assertEquals(submarine.depth, 6)
        Assert.assertEquals(submarine.aim, 0)
    }

    @Test
    fun whenMovingDown_whenAimEnabled_shouldOnlyIncreaseAim() {
        val submarine = Submarine(true)

        submarine.move("down", 6)

        Assert.assertEquals(submarine.depth, 0)
        Assert.assertEquals(submarine.aim, 6)
    }

    @Test
    fun whenMovingUp_shouldOnlyDecreaseDepth() {
        val submarine = Submarine()
        submarine.depth = 6

        submarine.move("up", 4)

        Assert.assertEquals(submarine.depth, 2)
        Assert.assertEquals(submarine.aim, 0)
    }

    @Test
    fun whenMovingUp_whenAimIsEnabled_shouldOnlyDecreaseAim() {
        val submarine = Submarine(true)
        submarine.aim = 6

        submarine.move("up", 4)

        Assert.assertEquals(submarine.depth, 0)
        Assert.assertEquals(submarine.aim, 2)
    }

    @Test
    fun whenMoving_shouldCalculatePositionMultipliedByDepth() {
        val submarine = Submarine()

        submarine.move("forward", 5)
        submarine.move("down", 5)
        submarine.move("forward", 8)
        submarine.move("up", 3)
        submarine.move("down", 8)
        submarine.move("forward", 2)

        Assert.assertEquals(submarine.calculation, 150)
    }

    @Test
    fun whenMoving_shouldAcceptMultipleCase() {
        val submarine = Submarine()

        submarine.move("fOrWARD", 5)
        submarine.move("Down", 5)
        submarine.move("ForwArd", 8)
        submarine.move("uP", 3)
        submarine.move("doWN", 8)
        submarine.move("forwaRD", 2)

        Assert.assertEquals(submarine.calculation, 150)
    }

    @Test
    fun whenMoving_whenAimEnabled_shouldCalculatePositionMultipliedByDepth() {
        val submarine = Submarine(true)

        submarine.move("forward", 5)
        submarine.move("down", 5)
        submarine.move("forward", 8)
        submarine.move("up", 3)
        submarine.move("down", 8)
        submarine.move("forward", 2)

        Assert.assertEquals(submarine.calculation, 900)
    }
}