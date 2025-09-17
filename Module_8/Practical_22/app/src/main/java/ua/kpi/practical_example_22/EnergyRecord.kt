package ua.kpi.practical_example_22

// Модель даних для одного запису про енергоспоживання
data class EnergyRecord(
    val date: String,      // Дата вимірювання
    val power: Double,      // Потужність у кВт
    val status: String = "Активна"  // Статус станції (наприклад: "Активна", "Помилка", "Тех. обслуговування")
)