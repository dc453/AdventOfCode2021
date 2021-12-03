import java.io.File

class DiagnosticReport(val report: List<String>) {
    private val gammaRateBits = report[0].foldIndexed("") { index, total, item ->
        var activeBits = 0
        report.forEach {
            if (it[index] == '1') activeBits++
        }
        total + if (activeBits > report.size / 2) "1" else "0"
    }
    private val epsilonRateBits = gammaRateBits
        .map { if (it == '1') '0' else '1' }
        .joinToString("")

    val gammaRate
        get() = Integer.parseInt(gammaRateBits, 2)
    val epsilonRate
        get() = Integer.parseInt(epsilonRateBits, 2)
    val powerConsumption
        get() = gammaRate * epsilonRate

}

fun main() {
    val rawReportData = File("src/main/inputs/Day3_BinaryDiagnostic.txt")
        .readLines()
    val report = DiagnosticReport(rawReportData)
    println("power consumption: ${report.powerConsumption}")
}