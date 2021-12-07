import java.io.File

class LanternfishSchool(input: String) {
    val fish: MutableList<Lanternfish> = input.split(",")
        .map { Lanternfish(internalTimer = it.toInt()) }
        .toMutableList()
    val newFishQueue: MutableList<Lanternfish> = mutableListOf()

    fun tickDay() {
        fish.forEach { it.tick() }
        newFishQueue.forEach {
            fish.add(it)
        }
        newFishQueue.clear()
    }

    fun addFish() {
        newFishQueue.add(Lanternfish())
    }


    inner class Lanternfish(var internalTimer: Int = 8) {
        fun tick() {
            if (internalTimer == 0) {
                addFish()
                internalTimer = 6
            } else {
                internalTimer--
            }
        }
    }
}

fun getNumFish(input: String, days: Int): Long {
    val startingFish: List<Int> = input.split(',')
        .map { it.toInt() }
        .toList()
    val fishLifecycles: MutableList<Long> = (0..6).map { 0.toLong() }.toMutableList()
    val newFishLifecycles: MutableList<Long> = (0..8).map { 0.toLong() }.toMutableList()
    val changingFish: MutableList<Long> = (0..6).map { 0.toLong() }.toMutableList()
    val newChangingFish: MutableList<Long> = (0..8).map { 0.toLong() }.toMutableList()

    startingFish.forEach {
        fishLifecycles[it] = fishLifecycles[it] + 1
    }

    for (day in 1..days) {
        (0..8)
            .forEach { fish ->
                when (fish) {
                    0 -> {
                        newChangingFish[8] = fishLifecycles[0] + newFishLifecycles[0]
                        changingFish[6] = fishLifecycles[0] + newFishLifecycles[0]
                    }
                    in 1..6 -> {
                        changingFish[fish - 1] = fishLifecycles[fish]
                        newChangingFish[fish - 1] = newFishLifecycles[fish]
                    }
                    in 7..8 -> {
                        newChangingFish[fish - 1] = newFishLifecycles[fish]
                    }
                }
            }
        (0..8)
            .forEach { fishIndex ->
                if (fishIndex < fishLifecycles.size) {
                    fishLifecycles[fishIndex] = changingFish[fishIndex]
                    changingFish[fishIndex] = 0
                }

                newFishLifecycles[fishIndex] = newChangingFish[fishIndex]
                newChangingFish[fishIndex] = 0
            }
    }

    return fishLifecycles.sum() + newFishLifecycles.sum()
}

fun main() {
    val input = File("src/main/inputs/Day6_Lanternfish.txt")
        .readText()
    val school = LanternfishSchool(input)
    for (i in 1..80) {
        school.tickDay()
    }
    println("after 80 days, there are ${school.fish.size} fish")

    val part2Fish = getNumFish(input = input, days = 256)
    println("after 256 days, there are ${part2Fish} fish")
}