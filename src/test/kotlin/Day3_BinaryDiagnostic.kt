import org.junit.Assert
import org.junit.Test

class Day3_BinaryDiagnostic_Tests {

    val testReport: List<String> =  listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010"
    )

    @Test
    fun whenCreatingDiagnosticReport_shouldCalculateGammaRate() {
        val report = DiagnosticReport(testReport)
        Assert.assertEquals(report.gammaRate, 22)
    }

    @Test
    fun whenCreatingDiagnosticReport_shouldCalculateEpsilonRate() {
        val report = DiagnosticReport(testReport)
        Assert.assertEquals(report.epsilonRate, 9)
    }

    @Test
    fun whenCreatingDiagnosticReport_shouldCalculatePowerConsumption() {
        val report = DiagnosticReport(testReport)
        Assert.assertEquals(report.powerConsumption, 198)
    }

    @Test
    fun whenCreatingDiagnosticReport_shouldCalculateOxygenGeneratorRating() {
        val report = DiagnosticReport(testReport)
        Assert.assertEquals(report.oxygenGeneratorRating, 23)
    }

    @Test
    fun whenCreatingDiagnosticReport_shouldCalculateCO2ScrubberRating() {
        val report = DiagnosticReport(testReport)
        Assert.assertEquals(report.cO2ScrubberRating, 10)
    }

    @Test
    fun whenCreatingDiagnosticReport_shouldCalculateLifeSupportRating() {
        val report = DiagnosticReport(testReport)
        Assert.assertEquals(report.lifeSupportRating, 230)
    }
}