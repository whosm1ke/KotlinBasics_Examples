package ua.kpi.practical_example_9.medium

// ------------------------------------------------------
// Local Data Source (імітація Room)
// ------------------------------------------------------
class LocalSolarDataSource {
    private val data = mutableListOf<SolarForecast>()

    fun getAll(): List<SolarForecast> = data.toList() // повертаємо копію списку
    fun insert(item: SolarForecast) { data.add(item) }
    fun update(item: SolarForecast) {
        data.replaceAll { if (it.id == item.id) item else it }
    }
    fun delete(id: Int) { data.removeIf { it.id == id } }
    fun findById(id: Int): SolarForecast? = data.find { it.id == id }
}