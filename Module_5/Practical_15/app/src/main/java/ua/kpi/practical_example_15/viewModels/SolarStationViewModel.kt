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
    // Початковий список станцій
    private val _stations = mutableStateListOf(
        SolarStation("SolarOne", "Solar", 500.0, LocalDate.of(2025, 9, 10), "Active"),
        SolarStation("SolarMax", "Solar", 750.0, LocalDate.of(2025, 9, 12), "Active"),
        SolarStation("WindPower", "Wind", 300.0, LocalDate.of(2025, 9, 11), "Maintenance"),
        SolarStation("HydroFlow", "Hydro", 1000.0, LocalDate.of(2025, 9, 9), "Active")
    )

    var searchQuery by mutableStateOf("")
    var sortBy by mutableStateOf("Name") // Name, Type, Power, Date
    var filterType by mutableStateOf<String?>(null) // null = всі типи

    // Відфільтрований та відсортований список для UI
    val filteredStations: List<SolarStation>
        get() {
            var result = _stations.toList()

            // --- Фільтрація по типу станції ---
            filterType?.let { type ->
                result = result.filter { it.type.equals(type, ignoreCase = true) }
            }

            // --- Пошук по назві станції ---
            if (searchQuery.isNotEmpty()) {
                result = result.filter { it.name.contains(searchQuery, ignoreCase = true) }
            }

            // --- Сортування ---
            result = when (sortBy) {
                "Name" -> result.sortedBy { it.name }
                "Type" -> result.sortedBy { it.type }
                "Power" -> result.sortedByDescending { it.power } // за потужністю
                "Date" -> result.sortedByDescending { it.lastUpdate }
                else -> result
            }

            return result
        }

    // Функції для зміни параметрів фільтра та сортування
    fun updateSearchQuery(query: String) { searchQuery = query }
    fun updateSortBy(criteria: String) { sortBy = criteria }
    fun updateFilterType(type: String?) { filterType = type }
}