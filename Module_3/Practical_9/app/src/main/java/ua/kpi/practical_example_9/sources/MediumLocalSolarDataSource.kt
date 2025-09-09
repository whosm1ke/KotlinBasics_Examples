package ua.kpi.practical_example_9.sources

import ua.kpi.practical_example_9.data.SolarPower

class MediumLocalSolarDataSource {
    private val _powerData = mutableListOf<SolarPower>()

    fun getAll(): List<SolarPower> = _powerData.toList()
    fun insert(power: SolarPower) { _powerData.add(power) }
    fun update(power: SolarPower) {
        _powerData.indexOfFirst { it.day == power.day }.takeIf { it != -1 }?.let { _powerData[it] = power }
    }
    fun delete(day: String) { _powerData.removeAll { it.day == day } }
    fun search(query: String): List<SolarPower> = _powerData.filter { it.day.contains(query) }
}

