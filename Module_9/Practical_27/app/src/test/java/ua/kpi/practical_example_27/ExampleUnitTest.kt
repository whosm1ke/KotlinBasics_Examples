package ua.kpi.practical_example_27

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
// Оголошення класу тестів для локального виконання
class ExampleUnitTest {
    @Test  // Анотація, що позначає метод як тестовий
    fun addition_isCorrect() {  // Тестова функція, яка перевіряє правильність додавання
        assertEquals(4, 2 + 2)  // Перевірка, чи результат виразу 2 + 2 дорівнює 4
    }
}