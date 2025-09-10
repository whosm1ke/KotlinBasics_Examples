package ua.kpi.practical_example_9.basic

// ------------------------------------------------------
// Repository — шар, що відокремлює бізнес-логіку від DataSource
// ------------------------------------------------------
class SolarRepository(private val local: LocalSolarDataSource) {
    fun getAll(): List<SolarForecast> = local.getAll()
    fun insert(item: SolarForecast) = local.insert(item)
}