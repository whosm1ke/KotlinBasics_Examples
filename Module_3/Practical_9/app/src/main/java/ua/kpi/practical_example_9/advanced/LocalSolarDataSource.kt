package ua.kpi.practical_example_9.advanced

class LocalSolarDataSource {
    // Внутрішнє сховище для прогнозів сонячної енергії
    private val data = mutableListOf<SolarForecast>()

    /**
     * Повертає всі дані у вигляді копії, щоб уникнути небажаних змін ззовні
     * @return список прогнозів сонячної енергії
     */
    fun getAll(): List<SolarForecast> = data.toList()

    /**
     * Додає новий прогноз у сховище
     * @param item прогноз для додавання
     */
    fun insert(item: SolarForecast) { data.add(item) }

    /**
     * Оновлює існуючий прогноз за ідентифікатором
     * @param item новий прогноз для оновлення
     */
    fun update(item: SolarForecast) {
        data.replaceAll { if (it.id == item.id) item else it }
    }

    /**
     * Видаляє прогноз за ідентифікатором
     * @param id ідентифікатор прогнозу для видалення
     */
    fun delete(id: Int) { data.removeIf { it.id == id } }

    /**
     * Знаходить прогноз за ідентифікатором
     * @param id ідентифікатор шуканого прогнозу
     * @return знайдений прогноз або null, якщо не знайдено
     */
    fun findById(id: Int): SolarForecast? = data.find { it.id == id }

    /**
     * Очищення локального кешу при потребі
     */
    fun clear() { data.clear() }

    /**
     * Фільтрація прогнозів по дню
     * @param day день для фільтрації (пошук нечутливий до регістру)
     * @return список прогнозів, що відповідають заданому дню
     */
    fun filterByDay(day: String): List<SolarForecast> =
        data.filter { it.day.contains(day, ignoreCase = true) }

    /**
     * Сортування прогнозів по потужності
     * @param descending true — сортування за спаданням, false — за зростанням
     * @return відсортований список прогнозів
     */
    fun sortByPower(descending: Boolean = false): List<SolarForecast> =
        if (descending) data.sortedByDescending { it.predictedPower }
        else data.sortedBy { it.predictedPower }
}