package ua.kpi.practical_example_9.basic

// ------------------------------------------------------
// Repository — шар, що відокремлює бізнес-логіку від DataSource
// Він надає інтерфейс для роботи з даними, приховуючи деталі їхнього зберігання
// ------------------------------------------------------

class SolarRepository(private val local: LocalSolarDataSource) {
    // Метод для отримання всіх прогнозів сонячної енергії з локального джерела даних
    fun getAll(): List<SolarForecast> = local.getAll()
    
    // Метод для вставки нового прогнозу сонячної енергії в локальне джерело даних
    fun insert(item: SolarForecast) = local.insert(item)
}