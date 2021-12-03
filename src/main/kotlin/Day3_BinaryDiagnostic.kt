import java.io.File

class DiagnosticReport(val report: List<String>) {
    private val gammaRateBits = report[0].foldIndexed("") { index, total, item ->
        var activeBits = 0
        report.forEach {
            if (it[index] == '1') activeBits++
        }
        total + if (activeBits >= report.size / 2) "1" else "0"
    }
    private val epsilonRateBits = gammaRateBits
        .map { if (it == '1') '0' else '1' }
        .joinToString("")
    private var oxygenGeneratorRatingBits = filterByMostCommonBitAtIndex(report)
    private var cO2ScrubberRatingBits = filterByMostCommonBitAtIndex(list = report, invert = true)

    val gammaRate
        get() = Integer.parseInt(gammaRateBits, 2)
    val epsilonRate
        get() = Integer.parseInt(epsilonRateBits, 2)
    val powerConsumption
        get() = gammaRate * epsilonRate
    val oxygenGeneratorRating
        get() = Integer.parseInt(oxygenGeneratorRatingBits, 2)
    val cO2ScrubberRating
        get() = Integer.parseInt(cO2ScrubberRatingBits, 2)
    val lifeSupportRating
        get() = oxygenGeneratorRating * cO2ScrubberRating

    private fun filterByMostCommonBitAtIndex(list: List<String>, bitIndex: Int = 0, invert: Boolean = false): String {
        if (list.size == 1) return list[0]

        var activeBits = 0
        list.forEach { listItem ->
            if (listItem[bitIndex] == '1') activeBits++
        }
        val commonBit = if (activeBits >= (list.size / 2.0)) {
            if (invert) '0' else '1'
        } else {
            if (invert) '1' else '0'
        }

        val filteredList = list.filter { it[bitIndex] == commonBit }
        return filterByMostCommonBitAtIndex(list = filteredList, bitIndex = bitIndex + 1, invert = invert)
    }
}

fun main() {
    val rawReportData = File("src/main/inputs/Day3_BinaryDiagnostic.txt")
        .readLines()
    val report = DiagnosticReport(rawReportData)
    println("power consumption: ${report.powerConsumption}")
    println("life support rating: ${report.lifeSupportRating}")
}