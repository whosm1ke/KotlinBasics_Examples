package ua.kpi.practical_example_9.advanced

class LocalSolarDataSource {
    private val data = mutableListOf<SolarForecast>()

    /** Повертає всі дані у вигляді копії, щоб уникнути небажаних змін */
    fun getAll(): List<SolarForecast> = data.toList()

    fun insert(item: SolarForecast) { data.add(item) }

    fun update(item: SolarForecast) {
        data.replaceAll { if (it.id == item.id) item else it }
    }

    fun delete(id: Int) { data.removeIf { it.id == id } }

    fun findById(id: Int): SolarForecast? = data.find { it.id == id }

    /** Очищення локального кешу при потребі */
    fun clear() { data.clear() }
    /** Фільтрація прогнозів по дню */
    fun filterByDay(day: String): List<SolarForecast> =
        data.filter { it.day.contains(day, ignoreCase = true) }

    /** Сортування прогнозів по потужності */
    fun sortByPower(descending: Boolean = false): List<SolarForecast> =
        if (descending) data.sortedByDescending { it.predictedPower }
        else data.sortedBy { it.predictedPower }
}