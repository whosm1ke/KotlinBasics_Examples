package ua.kpi.practical_example_14.data

// Дані про сонячні електростанції
data class SolarStation(
    val name: String,
    val type: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val forecastPower: Double // прогнозована потужність в кВт
)
