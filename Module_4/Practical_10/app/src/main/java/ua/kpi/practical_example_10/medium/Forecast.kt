package ua.kpi.practical_example_10.medium

data class Forecast(
    val id: Int,
    val stationId: Int,
    val date: String,
    val predictedPowerKw: Double,
    val temperatureC: Double,
    val cloudCoveragePercent: Int
)