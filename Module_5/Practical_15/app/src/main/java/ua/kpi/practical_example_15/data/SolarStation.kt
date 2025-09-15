package ua.kpi.practical_example_15.data

import java.time.LocalDate

// --- Модель даних для енергетичної станції ---
data class SolarStation(
    val name: String,
    val type: String, // наприклад: "Solar", "Wind", "Hydro"
    val power: Double, // Потужність у кВт
    val lastUpdate: LocalDate,
    val status: String // Наприклад: "Active", "Maintenance"
)

