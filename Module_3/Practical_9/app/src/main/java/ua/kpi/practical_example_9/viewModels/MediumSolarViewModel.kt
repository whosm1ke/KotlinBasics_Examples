package ua.kpi.practical_example_9.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.kpi.practical_example_9.api.FakeApiService
import ua.kpi.practical_example_9.api.SolarApiService
import ua.kpi.practical_example_9.data.SolarPower
import ua.kpi.practical_example_9.repositories.MediumSolarRepository
import ua.kpi.practical_example_9.sources.LocalSolarDataSource
import ua.kpi.practical_example_9.sources.MediumLocalSolarDataSource

class MediumSolarViewModel(private val repository: MediumSolarRepository) : ViewModel() {
    private val _powerList = MutableStateFlow<List<SolarPower>>(emptyList())
    val powerList: StateFlow<List<SolarPower>> get() = _powerList

    private val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    init {
        loadPowerData()
    }

    fun loadPowerData() {
        viewModelScope.launch {
            _powerList.value = repository.getAllPower()
        }
    }

    fun addPower(day: String, power: Double) {
        if (day.isNotBlank() && power >= 0.0) {
            viewModelScope.launch {
                repository.addPower(SolarPower(day, power))
                loadPowerData()
            }
        }
    }

    fun updatePower(day: String, power: Double) {
        viewModelScope.launch {
            repository.updatePower(SolarPower(day, power))
            loadPowerData()
        }
    }

    fun deletePower(day: String) {
        viewModelScope.launch {
            repository.deletePower(day)
            loadPowerData()
        }
    }

    fun searchPower(query: String) {
        _powerList.value = repository.searchPower(query)
    }
}

class MediumSolarViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val local = MediumLocalSolarDataSource()

        // --- Налаштування Retrofit-клієнта ---
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fakeapi.com/") // базовий URL потрібен для Retrofit
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Використовуємо фейковий сервіс для симуляції
        val remote: SolarApiService = FakeApiService()

        val repo = MediumSolarRepository(local, remote)
        return MediumSolarViewModel(repo) as T
    }
}