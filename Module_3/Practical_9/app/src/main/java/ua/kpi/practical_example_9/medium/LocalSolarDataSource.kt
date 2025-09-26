package ua.kpi.practical_example_9.medium

// ------------------------------------------------------
// Local Data Source (імітація Room)
// ------------------------------------------------------
class LocalSolarDataSource {
    // Приватне поле, яке зберігає дані у вигляді списку прогнозів сонячної енергії
    private val data = mutableListOf<SolarForecast>()

    // Метод для отримання всіх записів з локального джерела даних
    // Повертає копію внутрішнього списку, щоб запобігти зміненню оригіналу ззовні
    fun getAll(): List<SolarForecast> = data.toList()

    // Метод для додавання нового запису до локального сховища даних
    fun insert(item: SolarForecast) { data.add(item) }

    // Метод для оновлення існуючого запису за його id
    // Замінює старий об'єкт новим, якщо вони мають однаковий id
    fun update(item: SolarForecast) {
        data.replaceAll { if (it.id == item.id) item else it }
    }

    // Метод для видалення запису за його id
    fun delete(id: Int) { data.removeIf { it.id == id } }

    // Метод для пошуку запису за його унікальним id
    // Повертає знайдений об'єкт або null, якщо не знайдено
    fun findById(id: Int): SolarForecast? = data.find { it.id == id }
}