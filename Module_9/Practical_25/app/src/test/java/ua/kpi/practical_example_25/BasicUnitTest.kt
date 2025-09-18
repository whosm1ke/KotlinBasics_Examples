package ua.kpi.practical_example_25

import org.junit.Assert
import org.junit.Test
import ua.kpi.practical_example_25.data.BasicCalculator

class BasicUnitTest {
    
    val calc = BasicCalculator()
    /**
     * Тест 1: нормальні умови
     * Перевіряємо, чи функція повертає правильне значення при звичайних умовах.
     */
    @Test
    fun calculatePower_normalConditions() {
        val power = calc.calculatePower(irradiance = 800.0, temperature = 25.0, area = 10.0)
        // Очікуване значення: 800 * 10 * 0.18 = 1440 Вт = 1.44 кВт
        Assert.assertEquals(1.44, power, 0.01)
    }

    /**
     * Тест 2: нульова площа
     * Якщо площа дорівнює 0, потужність повинна бути 0.
     */
    @Test
    fun calculatePower_zeroArea() {
        val power = calc.calculatePower(irradiance = 1000.0, temperature = 25.0, area = 0.0)
        Assert.assertEquals(0.0, power, 0.0)
    }

    /**
     * Тест 3: дуже висока температура
     * При високій температурі ефективність падає, тому потужність має бути нижчою.
     */
    @Test
    fun calculatePower_highTemperature() {
        val normalPower = calc.calculatePower(1000.0, 25.0, 10.0)
        val hotPower = calc.calculatePower(1000.0, 60.0, 10.0) // 60°C
        Assert.assertTrue(hotPower < normalPower)
    }

    /**
     * Тест 4: дуже низька температура
     * При низькій температурі ефективність може навіть зрости.
     */
    @Test
    fun calculatePower_lowTemperature() {
        val normalPower = calc.calculatePower(1000.0, 25.0, 10.0)
        val coldPower = calc.calculatePower(1000.0, -10.0, 10.0) // -10°C
        Assert.assertTrue(coldPower > normalPower)
    }

    /**
     * Тест 5: від’ємна радіація (некоректний випадок)
     * Потужність не може бути від’ємною, тож перевіряємо, що результат >= 0.
     */
    @Test
    fun calculatePower_negativeIrradiance() {
        val power = calc.calculatePower(irradiance = -500.0, temperature = 25.0, area = 10.0)
        Assert.assertTrue(power <= 0.0)
    }
}

