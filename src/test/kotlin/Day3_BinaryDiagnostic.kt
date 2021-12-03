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
        Assert.assertEquals(22, report.gammaRate)
    }

    @Test
    fun whenCreatingDiagnosticReport_shouldCalculateEpsilonRate() {
        val report = DiagnosticReport(testReport)
        Assert.assertEquals(9, report.epsilonRate)
    }

    @Test
    fun whenCreatingDiagnosticReport_shouldCalculatePowerConsumption() {
        val report = DiagnosticReport(testReport)
        Assert.assertEquals(198, report.powerConsumption)
    }

    @Test
    fun whenCreatingDiagnosticReport_shouldCalculateOxygenGeneratorRating() {
        val report = DiagnosticReport(testReport)
        Assert.assertEquals(23, report.oxygenGeneratorRating)
    }

    @Test
    fun whenCreatingDiagnosticReport_shouldCalculateCO2ScrubberRating() {
        val report = DiagnosticReport(testReport)
        Assert.assertEquals(10, report.cO2ScrubberRating)
    }

    @Test
    fun whenCreatingDiagnosticReport_shouldCalculateLifeSupportRating() {
        val report = DiagnosticReport(testReport)
        Assert.assertEquals(230, report.lifeSupportRating)
    }
}