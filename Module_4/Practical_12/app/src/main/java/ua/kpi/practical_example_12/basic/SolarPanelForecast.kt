package ua.kpi.practical_example_12.basic

// Оголошення даних класу (data class) для зберігання прогнозу електроенергії від сонячних панелей
// Цей клас містить три основні поля: дату, потужність у кіловат-годинах та статус
// Data class автоматично генерує конструктор, toString, equals, hashCode і copy методи
data class SolarPanelForecast(
    // Дата прогнозу у форматі рядка (наприклад, "2023-10-01")
    val date: String,
    
    // Потужність електроенергії, яку вироблять сонячні панелі за день, у кіловат-годинах
    val powerKwh: Int,
    
    // Статус прогнозу (наприклад, "normal", "warning", "error")
    val status: String
)