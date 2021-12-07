import org.junit.Assert
import org.junit.Test

class Day6_Lanternfish_Tests {

    @Test
    fun schoolOfFish_shouldRecognizeExistingFish() {
        val school = LanternfishSchool("3,4,3,1,2")

        Assert.assertEquals(3, school.fish[0].internalTimer)
        Assert.assertEquals(4, school.fish[1].internalTimer)
        Assert.assertEquals(3, school.fish[2].internalTimer)
    }

    @Test
    fun schoolOfFish_whenAdvancingDay_shouldDecrementInternalTimers() {
        val school = LanternfishSchool("3,4,3,1,2")

        school.tickDay()

        Assert.assertEquals(2, school.fish[0].internalTimer)
        Assert.assertEquals(3, school.fish[1].internalTimer)
        Assert.assertEquals(2, school.fish[2].internalTimer)
    }

    @Test
    fun schoolOfFish_whenAdvancingDay_whenInternalTimerReachesZero_shouldSpawnNewFishWithInternalTimerOf8() {
        val school = LanternfishSchool("0,4")

        school.tickDay()

        Assert.assertEquals(8, school.fish[2].internalTimer)
    }

    @Test
    fun schoolOfFish_existingFish_whenAdvancingDay_whenInternalTimerIs0_shouldResetInternalTimerTo6() {
        val school = LanternfishSchool("0,4")

        school.tickDay()

        Assert.assertEquals(6, school.fish[0].internalTimer)
    }

    @Test
    fun schoolOfFish_whenAdvancingDay_shouldMultiplyAccordingly() {
        val school = LanternfishSchool("3,4,3,1,2")

        for (i in 1..18) {
            school.tickDay()
        }

        val expectedFishTimers = listOf<Int>(6,0,6,4,5,6,0,1,1,2,6,0,1,1,1,2,2,3,3,4,6,7,8,8,8,8)
        val actualFishTimers: List<Int> = school.fish.map {
            it.internalTimer
        }
        Assert.assertEquals(expectedFishTimers, actualFishTimers)
    }

    @Test
    fun schoolOfFish_shouldCalculateRateOfGrowth() {
        val numFish = getNumFish(input = "3,4,3,1,2", days = 18)
        Assert.assertEquals(26, numFish)

        val numFish2 = getNumFish(input = "3,4,3,1,2", days = 80)
        Assert.assertEquals(5934, numFish2)
    }
}