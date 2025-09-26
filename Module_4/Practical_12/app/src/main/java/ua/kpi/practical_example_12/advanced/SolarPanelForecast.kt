package ua.kpi.practical_example_12.advanced

// Оголошення data-класу для представлення прогнозу виробництва електроенергії сонячними панелями
// Data-клас автоматично генерує методи equals(), hashCode(), toString() та copy()
data class SolarPanelForecast(
    // Дата, на яку зроблено прогноз (у форматі рядка)
    val date: String,
    
    // Прогнозована кількість електроенергії в кіловат-годинах
    val powerKwh: Int,
    
    // Статус прогнозу (наприклад, "stable", "increasing", "decreasing")
    val status: String
)