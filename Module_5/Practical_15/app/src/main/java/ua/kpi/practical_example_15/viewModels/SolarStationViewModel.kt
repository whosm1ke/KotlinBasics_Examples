package ua.kpi.practical_example_15.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ua.kpi.practical_example_15.data.SolarStation
import java.time.LocalDate

// --- ViewModel для роботи зі списком станцій ---
class SolarStationViewModel : ViewModel() {
    // Початковий список станцій, що зберігається в mutableStateListOf для реактивного оновлення UI
    private val _stations = mutableStateListOf(
        SolarStation("SolarOne", "Solar", 500.0, LocalDate.of(2025, 9, 10), "Active"),
        SolarStation("SolarMax", "Solar", 750.0, LocalDate.of(2025, 9, 12), "Active"),
        SolarStation("WindPower", "Wind", 300.0, LocalDate.of(2025, 9, 11), "Maintenance"),
        SolarStation("HydroFlow", "Hydro", 1000.0, LocalDate.of(2025, 9, 9), "Active")
    )

    // Змінна для зберігання пошукового запиту, що використовується для фільтрації станцій
    var searchQuery by mutableStateOf("")
    
    // Змінна для визначення критерію сортування: Name, Type, Power, Date
    var sortBy by mutableStateOf("Name") // Name, Type, Power, Date
    
    // Змінна для фільтрації станцій за типом; null означає відображення всіх типів
    var filterType by mutableStateOf<String?>(null) // null = всі типи

    // Властивість, що повертає відфільтрований і відсортований список станцій для відображення у UI
    val filteredStations: List<SolarStation>
        get() {
            var result = _stations.toList() // Початкове копіювання списку

            // --- Фільтрація по типу станції ---
            filterType?.let { type ->
                result = result.filter { it.type.equals(type, ignoreCase = true) } // Відфільтрувати за типом (без врахування регістру)
            }

            // --- Пошук по назві станції ---
            if (searchQuery.isNotEmpty()) {
                result = result.filter { it.name.contains(searchQuery, ignoreCase = true) } // Відфільтрувати за назвою (без врахування регістру)
            }

            // --- Сортування ---
            result = when (sortBy) {
                "Name" -> result.sortedBy { it.name } // Сортування за назвою
                "Type" -> result.sortedBy { it.type } // Сортування за типом
                "Power" -> result.sortedByDescending { it.power } // Сортування за потужністю (спадаюче)
                "Date" -> result.sortedByDescending { it.lastUpdate } // Сортування за датою оновлення (спадаюче)
                else -> result // Якщо критерій не визначений, повернути без змін
            }

            return result // Повертаємо фінальний відфільтрований і відсортований список
        }

    // Функції для оновлення параметрів фільтра та сортування
    fun updateSearchQuery(query: String) { searchQuery = query } // Оновити пошуковий запит
    fun updateSortBy(criteria: String) { sortBy = criteria } // Оновити критерій сортування
    fun updateFilterType(type: String?) { filterType = type } // Оновити тип фільтра
}