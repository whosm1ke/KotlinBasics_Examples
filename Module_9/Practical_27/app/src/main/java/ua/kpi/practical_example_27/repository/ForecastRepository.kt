package ua.kpi.practical_example_27.repository

import ua.kpi.practical_example_27.models.SolarForecast
import java.text.SimpleDateFormat
import java.util.*

class ForecastRepository {

    // Метод отримує прогноз потужності на сьогоднішній день
    // Генерує дані з 6:00 до 18:00 кожні 2 години
    // Потужність випадкова у діапазоні від 1000 до 2000 кВт
    fun getTodayForecast(): List<SolarForecast> {
        val forecast = mutableListOf<SolarForecast>()
        val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        val calendar = Calendar.getInstance()
        for (hour in 6..18 step 2) { // прогноз з 6:00 до 18:00 кожні 2 години
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, 0)
            forecast.add(
                SolarForecast(
                    time = formatter.format(calendar.time),
                    powerKW = (1000..2000).random() // випадкова потужність у діапазоні 1000-2000 кВт
                )
            )
        }
        return forecast
    }

    // Метод отримує історичні дані прогнозу потужності за останні 7 днів
    // Кожен день містить прогнози з 6:00 до 18:00 кожні 6 годин
    // Потужність випадкова у діапазоні від 800 до 2000 кВт
    fun getHistoricalForecast(): List<SolarForecast> {
        val forecast = mutableListOf<SolarForecast>()
        val formatter = SimpleDateFormat("dd/MM HH:mm", Locale.getDefault())
        val calendar = Calendar.getInstance()
        for (dayOffset in 1..7) { // останні 7 днів
            calendar.add(Calendar.DAY_OF_MONTH, -dayOffset) // віднімаємо дні для отримання історичних дат
            for (hour in 6..18 step 6) { // прогноз кожні 6 годин з 6:00 до 18:00
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, 0)
                forecast.add(
                    SolarForecast(
                        time = formatter.format(calendar.time),
                        powerKW = (800..2000).random() // випадкова потужність у діапазоні 800-2000 кВт
                    )
                )
            }
        }
        return forecast
    }
}