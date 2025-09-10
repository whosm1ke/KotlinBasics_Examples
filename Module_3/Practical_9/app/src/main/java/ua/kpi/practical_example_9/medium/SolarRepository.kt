package ua.kpi.practical_example_9.medium

// ------------------------------------------------------
// Repository (об’єднує локальне та віддалене)
// ------------------------------------------------------
class SolarRepository(
    private val local: LocalSolarDataSource,
    private val remote: RemoteSolarDataSource
) {
    /** Отримати всі прогнози (спершу з локального кешу, якщо порожньо – з API) */
    suspend fun getAll(): List<SolarForecast> {
        val localData = local.getAll()
        if (localData.isNotEmpty()) return localData

        val remoteData = remote.fetchAll()
        remoteData.forEach { local.insert(it) } // кешуємо
        return remoteData
    }

    /** Додати прогноз */
    suspend fun add(item: SolarForecast): SolarForecast? {
        val added = remote.addForecast(item) ?: return null
        local.insert(added)
        return added
    }

    /** Оновити прогноз */
    suspend fun update(item: SolarForecast): SolarForecast? {
        val updated = remote.updateForecast(item.id, item) ?: return null
        local.update(updated)
        return updated
    }

    /** Видалити прогноз */
    suspend fun delete(id: Int): Boolean {
        val success = remote.deleteForecast(id)
        if (success) local.delete(id)
        return success
    }

    /** Знайти прогноз по id */
    fun findById(id: Int): SolarForecast? = local.findById(id)
}
