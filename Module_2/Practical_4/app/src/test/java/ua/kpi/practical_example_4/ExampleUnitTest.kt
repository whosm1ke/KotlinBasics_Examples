package ua.kpi.practical_example_4

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
// Оголошення класу тестів для локального виконання
class ExampleUnitTest {
    // Анотація @Test вказує, що цей метод є тестовою функцією
    @Test
    fun addition_isCorrect() {
        // Виклик методу assertEquals для перевірки рівності очікуваного і фактичного значення
        // Очікується, що 2 + 2 дорівнює 4
        assertEquals(4, 2 + 2)
    }
}