package ua.kpi.practical_example_9.basic

// ------------------------------------------------------
// Local Data Source — імітація бази даних у пам’яті
// ------------------------------------------------------

class LocalSolarDataSource {
    // Приватне поле, що містить список прогнозів сонячної енергії у пам'яті
    private val data = mutableListOf<SolarForecast>()

    // Метод для отримання всіх записів з симуляції бази даних
    fun getAll(): List<SolarForecast> = data
    
    // Метод для додавання нового запису у симуляцію бази даних
    fun insert(item: SolarForecast) { data.add(item) }
}