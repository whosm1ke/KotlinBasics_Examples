package ua.kpi.practical_example_5

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class) // Вказує, що цей тест буде виконуватися на Android пристрої з використанням AndroidJUnit4
class ExampleInstrumentedTest {
    @Test // Анотація, яка позначає метод як тестовий
    fun useAppContext() {
        // Отримуємо контекст тести, що виконується на цільовому пристрої
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        // Перевіряємо, чи співпадає назва пакета додатку з очікуваним значенням
        assertEquals("ua.kpi.practical_example_5", appContext.packageName)
    }
}