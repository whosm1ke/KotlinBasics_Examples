package ua.kpi.practical_example_10.medium

data class Forecast(
    // Унікальний ідентифікатор передбачення погоди
    val id: Int,
    // Ідентифікатор метеостанції, яка зробила прогноз
    val stationId: Int,
    // Дата прогнозу у вигляді рядка (наприклад, "2023-10-01")
    val date: String,
    // Прогнозована потужність у кіловаттах (KW)
    val predictedPowerKw: Double,
    // Температура повітря в градусах Цельсія
    val temperatureC: Double,
    // Відсоткове покриття хмар
    val cloudCoveragePercent: Int
)