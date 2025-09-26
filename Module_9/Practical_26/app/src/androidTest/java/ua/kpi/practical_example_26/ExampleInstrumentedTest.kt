package ua.kpi.practical_example_26

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
@RunWith(AndroidJUnit4::class)  // Вказуємо, що тест буде виконуватися на Android пристрої
class ExampleInstrumentedTest {
    @Test  // Позначаємо метод як тестовий
    fun useAppContext() {
        // Отримуємо контекст тести, що дозволяє отримати інформацію про цільовий додаток
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        // Перевіряємо, чи збігається назва пакету тести з очікуваним значенням
        assertEquals("ua.kpi.practical_example_26", appContext.packageName)
    }
}