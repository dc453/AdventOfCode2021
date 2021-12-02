import org.junit.Assert
import org.junit.Test

class Day2_Dive_Tests {

    @Test
    fun whenMovingForward_whenAimIsZero_shouldOnlyIncrementHorizontalPosition() {
        val submarine = Submarine()
        submarine.move("forward", 5)
        Assert.assertEquals(submarine.horizontalPosition, 5)
    }

    @Test
    fun whenMovingForward_shouldAcceptMultipleCase() {
        val submarine = Submarine()
        submarine.move("ForwARd", 5)
        Assert.assertEquals(submarine.horizontalPosition, 5)
    }

    @Test
    fun whenMovingForward_whenAimIsZero_shouldContinueToIncrementHorizontalPosition() {
        val submarine = Submarine()

        submarine.move("forward", 5)
        submarine.move("forward", 3)

        Assert.assertEquals(submarine.horizontalPosition, 8)
    }

    @Test
    fun whenMovingForward_whenAimIsNotZero_shouldAlsoIncreaseDepth() {
        val submarine = Submarine()
        submarine.aim = 3

        submarine.move("forward", 5)

        Assert.assertEquals(submarine.depth, 15)
    }

    @Test
    fun whenMovingDown_shouldIncreaseAim() {
        val submarine = Submarine()
        submarine.move("down", 6)
        Assert.assertEquals(submarine.aim, 6)
    }

    @Test
    fun whenMovingDown_shouldAcceptMultipleCase() {
        val submarine = Submarine()
        submarine.move("dOWn", 6)
        Assert.assertEquals(submarine.aim, 6)
    }

    @Test
    fun whenMovingDown_shouldContinueToIncreaseAim() {
        val submarine = Submarine()

        submarine.move("down", 6)
        submarine.move("down", 4)

        Assert.assertEquals(submarine.aim, 10)
    }

    @Test
    fun whenMovingUp_shouldDecreaseAim() {
        val submarine = Submarine()
        submarine.aim = 6

        submarine.move("up", 4)

        Assert.assertEquals(submarine.aim, 2)
    }

    @Test
    fun whenMovingUp_shouldAcceptMultipleCase() {
        val submarine = Submarine()
        submarine.aim = 6

        submarine.move("Up", 4)

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

        Assert.assertEquals(submarine.calculation, 900)
    }
}