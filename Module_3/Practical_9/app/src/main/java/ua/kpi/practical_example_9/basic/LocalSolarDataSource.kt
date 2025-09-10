package ua.kpi.practical_example_9.basic

// ------------------------------------------------------
// Local Data Source — імітація бази даних у пам’яті
// ------------------------------------------------------
class LocalSolarDataSource {
    private val data = mutableListOf<SolarForecast>()

    fun getAll(): List<SolarForecast> = data
    fun insert(item: SolarForecast) { data.add(item) }
}