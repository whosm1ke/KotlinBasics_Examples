package ua.kpi.practical_example_25

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import ua.kpi.practical_example_25.data.AdvancedCalculator

class AdvancedCalculatorTest {

    private val calculator = AdvancedCalculator()

    @Test
    fun testCalculatePowerAsync_defaultValues() = runBlocking {
        // Дані з умовними параметрами
        val result = calculator.calculatePowerAsync(1000.0, 25.0, 10.0)

        // Очікуваний результат: 1000 * 10 * 0.18 / 1000 = 1.8 кВт
        assertEquals(1.8, result, 0.01)
    }

    @Test
    fun testCalculatePowerAsync_withTemperatureEffect() = runBlocking {
        // При вищій температурі ефективність знижується
        val result = calculator.calculatePowerAsync(1000.0, 40.0, 10.0)

        // Очікуваний результат менший за 1.8 кВт
        assert(result < 1.8)
    }

    @Test
    fun testCalculatePowerAsync_withCoolTemperature() = runBlocking {
        // При низькій температурі ефективність зростає
        val result = calculator.calculatePowerAsync(1000.0, 0.0, 10.0)

        // Очікуваний результат більший за 1.8 кВт
        assert(result > 1.8)
    }
}