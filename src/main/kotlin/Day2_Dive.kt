import java.io.File
import java.util.*

class Submarine() {
    var horizontalPosition = 0
    var depth = 0
    val calculation
        get() = horizontalPosition * depth

    fun move(command: String, value: Int) {
        when (command.lowercase(Locale.getDefault())) {
            "forward" -> horizontalPosition += value
            "down" -> depth += value
            "up" -> depth -= value
        }
    }
}

fun main(args: Array<String>) {
    val submarine = Submarine()
    File("src/main/inputs/Day2_Dive.txt")
        .useLines { it ->
            it.toList()
        }
        .map {
            val parts = it.split(" ")
            submarine.move(parts[0], parts[1].toInt())
        }

    println("horizontalPosition * depth = ${submarine.calculation}")
}