package ua.kpi.practical_example_9.sources

import ua.kpi.practical_example_9.data.SolarPower

class AdvancedLocalSolarDataSource {
    private val _data = mutableListOf<SolarPower>()

    fun getAll(): List<SolarPower> = _data.toList()
    fun insert(power: SolarPower) { _data.add(power) }
    fun update(power: SolarPower) {
        _data.indexOfFirst { it.day == power.day }.takeIf { it != -1 }?.let { _data[it] = power }
    }
    fun delete(day: String) { _data.removeAll { it.day == day } }
}