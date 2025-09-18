package ua.kpi.practical_example_27.repository

import ua.kpi.practical_example_27.models.SolarForecast
import java.text.SimpleDateFormat
import java.util.*

class ForecastRepository {

    // Генеруємо фейкові дані прогнозу потужності
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
                    powerKW = (1000..2000).random()
                )
            )
        }
        return forecast
    }

    // Історичні дані (симуляція)
    fun getHistoricalForecast(): List<SolarForecast> {
        val forecast = mutableListOf<SolarForecast>()
        val formatter = SimpleDateFormat("dd/MM HH:mm", Locale.getDefault())
        val calendar = Calendar.getInstance()
        for (dayOffset in 1..7) {
            calendar.add(Calendar.DAY_OF_MONTH, -dayOffset)
            for (hour in 6..18 step 6) {
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, 0)
                forecast.add(
                    SolarForecast(
                        time = formatter.format(calendar.time),
                        powerKW = (800..2000).random()
                    )
                )
            }
        }
        return forecast
    }
}