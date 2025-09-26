package ua.kpi.practical_example_25

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import ua.kpi.practical_example_25.data.AdvancedCalculator

class AdvancedCalculatorTest {

    // Створення екземпляра калькулятора для тестування
    private val calculator = AdvancedCalculator()

    @Test
    fun testCalculatePowerAsync_defaultValues() = runBlocking {
        // Виклик методу обчислення потужності з типовими значеннями:
        // P = 1000 (потужність) * 25 (ефективність) * 10 (коефіцієнт)
        // Результат поділяється на 1000 для отримання кВт
        val result = calculator.calculatePowerAsync(1000.0, 25.0, 10.0)

        // Перевірка, чи результат дорівнює очікуваному значенню 1.8 кВт з точністю до 0.01
        assertEquals(1.8, result, 0.01)
    }

    @Test
    fun testCalculatePowerAsync_withTemperatureEffect() = runBlocking {
        // Виклик методу з вищою температурою (40°C), що знижує ефективність
        val result = calculator.calculatePowerAsync(1000.0, 40.0, 10.0)

        // Перевірка, чи результат менший за очікуване значення 1.8 кВт
        assert(result < 1.8)
    }

    @Test
    fun testCalculatePowerAsync_withCoolTemperature() = runBlocking {
        // Виклик методу з низькою температурою (0°C), що збільшує ефективність
        val result = calculator.calculatePowerAsync(1000.0, 0.0, 10.0)

        // Перевірка, чи результат більший за очікуване значення 1.8 кВт
        assert(result > 1.8)
    }
}