package ua.kpi.practical_example_9.basic

// ------------------------------------------------------
// Модель даних (entity для локального сховища)
// ------------------------------------------------------
data class SolarForecast(
    val id: Int,
    val day: String,
    val predictedPower: Double
)

