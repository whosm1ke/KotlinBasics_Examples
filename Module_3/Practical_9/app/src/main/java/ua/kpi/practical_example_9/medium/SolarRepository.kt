package ua.kpi.practical_example_9.medium

// ------------------------------------------------------
// Repository (об’єднує локальне та віддалене)
// ------------------------------------------------------
class SolarRepository(
    private val local: LocalSolarDataSource,  // Локальне джерело даних (наприклад, база даних або кеш)
    private val remote: RemoteSolarDataSource  // Віддалене джерело даних (API сервер)
) {
    /**
     * Отримати всі прогнози сонячної енергії.
     * Спершу перевіряємо локальний кеш, якщо він не порожній — повертаємо дані з нього.
     * Якщо ж кеш порожній — завантажуємо дані з API та кешуємо їх локально.
     */
    suspend fun getAll(): List<SolarForecast> {
        val localData = local.getAll()  // Отримуємо всі дані з локального джерела
        if (localData.isNotEmpty()) return localData  // Якщо є дані — повертаємо їх

        val remoteData = remote.fetchAll()  // Якщо немає локальних даних — отримуємо з API
        remoteData.forEach { local.insert(it) }  // Кешуємо отримані дані локально
        return remoteData  // Повертаємо дані з API
    }

    /**
     * Додати новий прогноз сонячної енергії.
     * Спочатку додаємо його на віддаленому сервері, потім кешуємо локально.
     * Якщо не вдалося додати на сервері — повертаємо null.
     */
    suspend fun add(item: SolarForecast): SolarForecast? {
        val added = remote.addForecast(item) ?: return null  // Надсилаємо дані на сервер, якщо не вдалося — повертаємо null
        local.insert(added)  // Кешуємо успішно доданий прогноз локально
        return added  // Повертаємо доданий прогноз
    }

    /**
     * Оновити існуючий прогноз сонячної енергії.
     * Спочатку оновлюємо дані на сервері за допомогою ID, потім оновлюємо локально.
     * Якщо не вдалося оновити на сервері — повертаємо null.
     */
    suspend fun update(item: SolarForecast): SolarForecast? {
        val updated = remote.updateForecast(item.id, item) ?: return null  // Оновлюємо дані на сервері
        local.update(updated)  // Оновлюємо локальну копію
        return updated  // Повертаємо оновлені дані
    }

    /**
     * Видалити прогноз сонячної енергії за ID.
     * Спочатку видаляємо на сервері, потім локально.
     * Повертаємо результат операції (true — успішно, false — не вдалося).
     */
    suspend fun delete(id: Int): Boolean {
        val success = remote.deleteForecast(id)  // Видаляємо з сервера
        if (success) local.delete(id)  // Якщо успішно — видаляємо локально
        return success  // Повертаємо результат операції
    }

    /**
     * Знайти прогноз за ID у локальному сховищі.
     * Це швидкий пошук без звернення до API.
     */
    fun findById(id: Int): SolarForecast? = local.findById(id)
}