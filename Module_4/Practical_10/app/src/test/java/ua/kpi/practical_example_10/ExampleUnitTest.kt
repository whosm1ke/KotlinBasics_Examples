package ua.kpi.practical_example_10

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        // Виконуємо перевірку правильності додавання чисел 2 та 2
        // Метод assertEquals порівнює очікуване значення (4) з фактичним (2 + 2)
        assertEquals(4, 2 + 2)
    }
}