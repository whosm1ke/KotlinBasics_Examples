package ua.kpi.practical_example_4

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
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Отримуємо контекст тестування, використовуючи InstrumentationRegistry
        // Це дозволяє отримати доступ до контексту додатку, який тестується
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        
        // Перевіряємо, чи збігається назва пакета додатка з очікуваним значенням
        // Це гарантує, що тест працює на правильному додатку
        assertEquals("ua.kpi.practical_example_4", appContext.packageName)
    }
}