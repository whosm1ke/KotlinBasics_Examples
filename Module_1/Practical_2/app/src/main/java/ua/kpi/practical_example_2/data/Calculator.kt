package ua.kpi.practical_example_2.data

class Calculator {
    fun calculatePowerAdvanced(radiation: String, efficiency: String, area: String): Double {
        val rad = radiation.toDoubleOrNull() ?: 0.0
        val eff = efficiency.toDoubleOrNull()?.div(100) ?: 0.0
        val a = area.toDoubleOrNull() ?: 0.0
        // Обчислення потужності з урахуванням площі панелі
        return rad * eff * a
    }
}