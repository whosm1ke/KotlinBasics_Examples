package ua.kpi.practical_example_23

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
        // Це дозволяє отримати доступ до контексту застосунку під час тестування
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        
        // Порівнюємо отриманий пакетний ім'я застосунку з очікуваним
        // Це гарантує, що тест виконується в правильному застосунку
        assertEquals("ua.kpi.practical_example_23", appContext.packageName)
    }
}