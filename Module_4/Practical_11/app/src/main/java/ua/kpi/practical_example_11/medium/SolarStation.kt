package ua.kpi.practical_example_11.medium

data class SolarStation(
    // Унікальний ідентифікатор сонячної станції
    val Id: Int,
    
    // Назва сонячної станції
    val Name: String,
    
    // Максимальна потужність станції в мегаватах (MW)
    val CapacityMW: Double
)