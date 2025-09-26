package ua.kpi.practical_example_12.medium

// Клас даних для представлення прогнозу виробництва електроенергії сонячними панелями
// Включає інформацію про дату, потужність у кіловат-годинах та статус

data class SolarPanelForecast(
    // Дата прогнозу виробництва електроенергії
    val date: String,
    
    // Потужність виробництва у кіловат-годинах (кВт·год)
    val powerKwh: Int,
    
    // Статус прогнозу (наприклад, "normal", "warning", "error")
    val status: String
)