package ua.kpi.practical_example_10.medium

data class ForecastStats(
    val stationId: Int,
    val averagePowerKw: Double,
    val averageTemperatureC: Double,
    val averageCloudCoverage: Double,
    val efficiency: Double
)