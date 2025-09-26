package ua.kpi.practical_example_25

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
@RunWith(AndroidJUnit4::class) // Вказує, що цей тест буде виконуватися з використанням AndroidJUnit4
class ExampleInstrumentedTest {
    @Test // Позначає метод як тестовий
    fun useAppContext() {
        // Отримуємо контекст тести, що дозволяє отримати інформацію про тести та застосунок
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        // Перевіряємо, чи відповідає назва пакета тестованого застосунку очікуваному значенню
        assertEquals("ua.kpi.practical_example_25", appContext.packageName)
    }
}