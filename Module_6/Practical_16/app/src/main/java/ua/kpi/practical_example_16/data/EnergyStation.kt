package ua.kpi.practical_example_16.data

data class EnergyStation(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val type: String = "",
    val description: String = "",
    val power: Int = 0, // Потужність у кВт
    val status: String = "", // Наприклад: "Активна", "На ремонті"
)