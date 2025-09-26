package ua.kpi.practical_example_9.advanced

class SolarRepository(
    private val local: LocalSolarDataSource, // Локальне джерело даних для кешування
    private val remote: RemoteSolarDataSource // Віддалене джерело даних для отримання актуальних даних
) {

    private val cacheTTL = 5 * 60 * 1000L // Час життя кешу — 5 хвилин у мілісекундах
    private var lastFetchTime = 0L // Час останнього запиту до віддаленого джерела

    /**
     * Основний метод отримання всіх прогнозів сонячної енергії з можливістю кешування та оновлення
     * @param forceRefresh - якщо true, завжди оновлює дані з віддаленого джерела
     * @return Result<List<SolarForecast>> - результат операції або помилка
     */
    suspend fun getAll(forceRefresh: Boolean = false): Result<List<SolarForecast>> {
        val currentTime = System.currentTimeMillis() // Отримуємо поточний час у мілісекундах
        val localData: List<SolarForecast> = local.getAll() // Отримуємо дані з локального кешу

        // Якщо не вимагається оновлення та є актуальні дані у кеші — повертаємо їх
        if (!forceRefresh && localData.isNotEmpty() && (currentTime - lastFetchTime) < cacheTTL) {
            return Result.success(localData)
        }

        return try {
            // Отримуємо дані з віддаленого джерела, якщо немає помилки
            val forecasts: List<SolarForecast> = remote.fetchAll().getOrNull() ?: emptyList()
            local.clear() // Очищуємо локальний кеш перед оновленням
            forecasts.forEach { local.insert(it) } // Вставляємо нові дані в локальний кеш
            lastFetchTime = currentTime // Оновлюємо час останнього запиту

            val avgPower = forecasts.map { it.predictedPower }.average() // Обчислюємо середню передбачувану потужність
            println("Average predicted power: $avgPower kW")

            Result.success(forecasts) // Повертаємо успішний результат з отриманими прогнозами
        } catch (e: Exception) {
            // Якщо виникла помилка, повертаємо дані з кешу, якщо вони є
            if (localData.isNotEmpty()) Result.success(localData)
            else Result.failure<List<SolarForecast>>(e) // Інакше повертаємо помилку
        }
    }

    /**
     * Додає новий прогноз сонячної енергії
     * @param forecast - прогноз для додавання
     * @return Result<SolarForecast> - результат операції або помилка
     */
    suspend fun add(forecast: SolarForecast): Result<SolarForecast> = try {
        // Надсилаємо запит на додавання прогнозу до віддаленого сервера
        val added: SolarForecast = remote.addForecast(forecast).getOrNull()
            ?: throw Exception("Failed to add forecast")
        local.insert(added) // Зберігаємо новий прогноз у локальному кеші
        Result.success(added)
    } catch (e: Exception) {
        Result.failure(e)
    }

    /**
     * Оновлює існуючий прогноз сонячної енергії
     * @param forecast - прогноз для оновлення
     * @return Result<SolarForecast> - результат операції або помилка
     */
    suspend fun update(forecast: SolarForecast): Result<SolarForecast> = try {
        // Надсилаємо запит на оновлення прогнозу на віддаленому сервері
        val updated: SolarForecast = remote.updateForecast(forecast.id, forecast).getOrNull()
            ?: throw Exception("Failed to update forecast")
        local.update(updated) // Оновлюємо прогноз у локальному кеші
        Result.success(updated)
    } catch (e: Exception) {
        Result.failure(e)
    }

    /**
     * Видаляє прогноз за ідентифікатором
     * @param id - ідентифікатор прогнозу для видалення
     * @return Result<Boolean> - результат операції (true — успішно, false — помилка)
     */
    suspend fun delete(id: Int): Result<Boolean> = try {
        // Видаляємо прогноз на віддаленому сервері
        val success: Boolean = remote.deleteForecast(id).isSuccess
        if (success) local.delete(id) // Якщо успішно, видаляємо з локального кешу
        Result.success(success)
    } catch (e: Exception) {
        Result.failure(e)
    }

    /**
     * Фільтрує прогнози за днем
     * @param day - день для фільтрації
     * @return список прогнозів, що відповідають заданому дню
     */
    fun filterByDay(day: String): List<SolarForecast> {
        return local.filterByDay(day)
    }

    /**
     * Сортує прогнози за передбачуваною потужністю
     * @param descending - якщо true — сортування за спаданням, інакше за зростанням
     * @return відсортований список прогнозів
     */
    fun sortByPower(descending: Boolean): List<SolarForecast> {
        return local.sortByPower(descending)
    }
}