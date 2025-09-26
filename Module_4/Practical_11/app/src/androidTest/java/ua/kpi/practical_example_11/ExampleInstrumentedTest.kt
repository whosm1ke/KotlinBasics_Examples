package ua.kpi.practical_example_11

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class) // Указуємо, що цей тест буде виконуватися на Android пристрої
class ExampleInstrumentedTest {
    @Test // Позначаємо метод як тестовий
    fun useAppContext() {
        // Отримуємо контекст додатку, який тести виконують
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        // Перевіряємо, чи відповідає назва пакета очікуваному значенню
        assertEquals("ua.kpi.practical_example_11", appContext.packageName)
    }
}