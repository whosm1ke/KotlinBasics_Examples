package ua.kpi.practical_example_7

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
        // Отримуємо контекст тестування за допомогою InstrumentationRegistry
        // Це дозволяє отримати доступ до контексту додатку, який тестується
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        
        // Перевіряємо, чи відповідає назва пакета додатка очікуваному значенню
        // Якщо назва пакета не збігається, тест буде вважатися проваленим
        assertEquals("ua.kpi.practical_example_7", appContext.packageName)
    }
}