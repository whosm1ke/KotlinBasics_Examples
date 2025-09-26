package ua.kpi.practical_example_9.advanced

data class SolarForecast(
    // Унікальний ідентифікатор прогнозу сонячної енергії
    val id: Int,
    
    // День, на який зроблено прогноз (наприклад, "2023-10-01")
    val day: String,
    
    // Прогнозована потужність сонячної енергії в кіловатах
    val predictedPower: Double,
    
    // Додаткові нотатки щодо прогнозу (може бути null)
    val notes: String? = null
)