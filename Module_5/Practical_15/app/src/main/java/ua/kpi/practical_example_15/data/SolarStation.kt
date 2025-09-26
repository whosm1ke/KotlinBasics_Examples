package ua.kpi.practical_example_15.data

import java.time.LocalDate

// --- Модель даних для енергетичної станції ---
// Цей data class представляє інформацію про сонячну станцію або іншу енергетичну установку
// Він містить основні характеристики станції, такі як назва, тип, потужність та статус

data class SolarStation(
    val name: String,                    // Назва енергетичної станції (наприклад, "Сонячна станція №1")
    val type: String,                     // Тип енергетичної станції (наприклад: "Solar", "Wind", "Hydro")
    val power: Double,                    // Потужність станції у кіловатах (кВт)
    val lastUpdate: LocalDate,            // Дата останнього оновлення даних про станцію
    val status: String                    // Поточний статус станції (наприклад: "Active", "Maintenance")
)