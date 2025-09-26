package ua.kpi.practical_example_19

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
@RunWith(AndroidJUnit4::class) // Указывает JUnit, що тест буде виконуватися на Android пристрої
class ExampleInstrumentedTest {
    @Test // Позначає метод як тестовий
    fun useAppContext() {
        // Отримуємо контекст тестованого додатку через реєстр інструментів
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        // Перевіряємо, чи відповідає назва пакета очікуваному значенню
        assertEquals("ua.kpi.practical_example_19", appContext.packageName)
    }
}