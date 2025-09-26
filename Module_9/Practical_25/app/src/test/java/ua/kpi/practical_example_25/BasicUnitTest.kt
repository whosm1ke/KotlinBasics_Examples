package ua.kpi.practical_example_25

import org.junit.Assert
import org.junit.Test
import ua.kpi.practical_example_25.data.BasicCalculator

class BasicUnitTest {
    
    // Створюємо екземпляр калькулятора для тестування
    val calc = BasicCalculator()
    
    /**
     * Тест 1: нормальні умови
     * Перевіряємо, чи функція повертає правильне значення при звичайних умовах.
     */
    @Test
    fun calculatePower_normalConditions() {
        // Викликаємо метод обчислення потужності з вхідними параметрами:
        // інтенсивність світла = 800 Вт/м², температура = 25°C, площа = 10 м²
        val power = calc.calculatePower(irradiance = 800.0, temperature = 25.0, area = 10.0)
        // Очікуване значення: 800 * 10 * 0.18 = 1440 Вт = 1.44 кВт
        // Порівнюємо отримане значення з очікуваним з допустимою похибкою 0.01
        Assert.assertEquals(1.44, power, 0.01)
    }

    /**
     * Тест 2: нульова площа
     * Якщо площа дорівнює 0, потужність повинна бути 0.
     */
    @Test
    fun calculatePower_zeroArea() {
        // Викликаємо метод з площею 0
        val power = calc.calculatePower(irradiance = 1000.0, temperature = 25.0, area = 0.0)
        // Очікуємо, що потужність буде 0
        Assert.assertEquals(0.0, power, 0.0)
    }

    /**
     * Тест 3: дуже висока температура
     * При високій температурі ефективність падає, тому потужність має бути нижчою.
     */
    @Test
    fun calculatePower_highTemperature() {
        // Обчислюємо потужність при стандартній температурі 25°C
        val normalPower = calc.calculatePower(1000.0, 25.0, 10.0)
        // Обчислюємо потужність при високій температурі 60°C
        val hotPower = calc.calculatePower(1000.0, 60.0, 10.0) // 60°C
        // Перевіряємо, що потужність при високій температурі менша
        Assert.assertTrue(hotPower < normalPower)
    }

    /**
     * Тест 4: дуже низька температура
     * При низькій температурі ефективність може навіть зрости.
     */
    @Test
    fun calculatePower_lowTemperature() {
        // Обчислюємо потужність при стандартній температурі 25°C
        val normalPower = calc.calculatePower(1000.0, 25.0, 10.0)
        // Обчислюємо потужність при низькій температурі -10°C
        val coldPower = calc.calculatePower(1000.0, -10.0, 10.0) // -10°C
        // Перевіряємо, що потужність при низькій температурі більша
        Assert.assertTrue(coldPower > normalPower)
    }

    /**
     * Тест 5: від’ємна радіація (некоректний випадок)
     * Потужність не може бути від’ємною, тож перевіряємо, що результат >= 0.
     */
    @Test
    fun calculatePower_negativeIrradiance() {
        // Викликаємо метод з від’ємною інтенсивністю світла
        val power = calc.calculatePower(irradiance = -500.0, temperature = 25.0, area = 10.0)
        // Перевіряємо, що результат не від'ємний (може бути 0 або додатній)
        Assert.assertTrue(power <= 0.0)
    }
}