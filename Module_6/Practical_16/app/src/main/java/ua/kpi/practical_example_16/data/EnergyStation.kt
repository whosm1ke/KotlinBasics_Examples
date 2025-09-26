package ua.kpi.practical_example_16.data

data class EnergyStation(
    // Назва енергостанції
    val name: String,
    
    // Широта місця розташування енергостанції
    val latitude: Double,
    
    // Довгота місця розташування енергостанції
    val longitude: Double,
    
    // Тип енергостанції (наприклад, вітряна, сонячна, гідро)
    val type: String = "",
    
    // Опис енергостанції
    val description: String = "",
    
    // Потужність енергостанції в кіловатах (кВт)
    val power: Int = 0,
    
    // Статус енергостанції (наприклад, "Активна", "На ремонті")
    val status: String = "",
)