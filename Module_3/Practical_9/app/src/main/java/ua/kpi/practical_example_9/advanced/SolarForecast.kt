package ua.kpi.practical_example_9.advanced

data class SolarForecast(
    val id: Int,
    val day: String,
    val predictedPower: Double,
    val notes: String? = null
)

