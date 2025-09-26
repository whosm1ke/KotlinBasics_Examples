package ua.kpi.practical_example_27.models

data class SolarForecast(
    // Поле, що зберігає час прогнозу сонячної енергії у форматі рядка (наприклад, "10:00")
    val time: String,
    
    // Поле, що зберігає передбачувану потужність сонячної енергії в кіловатних (KW)
    val powerKW: Int
)