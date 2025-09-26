package ua.kpi.practical_example_10.medium

data class ForecastStats(
    // Унікальний ідентифікатор станції, що надає прогноз
    val stationId: Int,
    
    // Середня потужність у кіловаттах (KW), отримана з прогнозу
    val averagePowerKw: Double,
    
    // Середня температура в градусах Цельсія, отримана з прогнозу
    val averageTemperatureC: Double,
    
    // Середнє покриття хмар у відсотках, отримане з прогнозу
    val averageCloudCoverage: Double,
    
    // Коефіцієнт ефективності, що характеризує ефективність роботи станції
    val efficiency: Double
)