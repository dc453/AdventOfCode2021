import java.io.File
import java.util.*

class Submarine(private val aimEnabled: Boolean = false) {
    var horizontalPosition = 0
    var depth = 0
    var aim = 0
    val calculation
        get() = horizontalPosition * depth

    fun move(command: String, value: Int) {
        when (command.lowercase(Locale.getDefault())) {
            "forward" -> {
                horizontalPosition += value
                if (aimEnabled) depth += (aim * value)
            }
            "down" -> {
                if (aimEnabled) {
                    aim += value
                } else {
                    depth += value
                }
            }
            "up" -> {
                if (aimEnabled) {
                    aim -= value
                } else {
                    depth -= value
                }
            }
        }
    }
}

fun main() {
    val submarine = Submarine()
    File("src/main/inputs/Day2_Dive.txt")
        .useLines {
            it.toList()
        }
        .map {
            val parts = it.split(" ")
            submarine.move(parts[0], parts[1].toInt())
        }

    println("horizontalPosition * depth = ${submarine.calculation}")
}