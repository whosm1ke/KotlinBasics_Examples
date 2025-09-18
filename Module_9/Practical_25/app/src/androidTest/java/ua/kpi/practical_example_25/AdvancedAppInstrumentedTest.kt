package ua.kpi.practical_example_25

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.isDialog
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class AdvancedAppInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    /**
     * Допоміжна функція: перемикаємо на AdvancedApp через DisplayModeSelector
     */
    private fun switchToAdvancedApp() {
        // Чекаємо селектор
        composeTestRule.onNodeWithTag("DisplayModeSelector").assertExists()

        // Клікаємо на кнопку "Просунутий рівень"
        composeTestRule.onNodeWithText("Просунутий рівень").performClick()

        // Переконуємось, що елементи AdvancedApp відображені
        composeTestRule.onNodeWithTag("SolarRadiation").assertExists()
        composeTestRule.onNodeWithTag("Temperature").assertExists()
        composeTestRule.onNodeWithTag("PanelArea").assertExists()
        composeTestRule.onNodeWithTag("CalculateButton").assertExists()
    }

    /**
     * Тест 1: Введення даних та асинхронний розрахунок
     */
    @Test
    fun testInputAndCalculateWithTags() {
        switchToAdvancedApp()

        // Вводимо значення
        composeTestRule.onNodeWithTag("SolarRadiation").performTextInput("1000")
        composeTestRule.onNodeWithTag("Temperature").performTextInput("25")
        composeTestRule.onNodeWithTag("PanelArea").performTextInput("10")

        // Натискаємо кнопку
        composeTestRule.onNodeWithTag("CalculateButton").performClick()

        // Очікуємо появу прогрес-індикатора
        composeTestRule.waitUntil(timeoutMillis = 3000) {
            composeTestRule.onAllNodesWithTag("CircularProgressIndicator").fetchSemanticsNodes().isNotEmpty()
        }

        // Очікуємо появу результату
        composeTestRule.waitUntil(timeoutMillis = 9000) {
            composeTestRule.onAllNodesWithTag("Result").fetchSemanticsNodes().isNotEmpty()
        }

        // Перевіряємо відображення результату
        composeTestRule.onNodeWithTag("Result").assertExists().assertIsDisplayed()
    }

    /**
     * Тест 2: Використання мокованих значень за замовчуванням
     */
    @Test
    fun testDefaultMockValuesWithTags() {
        switchToAdvancedApp()

        // Натискаємо кнопку без введення даних
        composeTestRule.onNodeWithTag("CalculateButton").performClick()

        // Очікуємо появу результату
        composeTestRule.waitUntil(timeoutMillis = 9000) {
            composeTestRule.onAllNodesWithTag("Result").fetchSemanticsNodes().isNotEmpty()
        }

        // Перевіряємо, що результат видно
        composeTestRule.onNodeWithTag("Result").assertExists().assertIsDisplayed()
    }

    /**
     * Тест 3: Порожні поля – використовуються моковані значення
     */
    @Test
    fun testEmptyFieldsUseDefaultValues() {
        switchToAdvancedApp()

        // Натискаємо кнопку без введення даних
        composeTestRule.onNodeWithTag("CalculateButton").performClick()

        // Очікуємо появу прогрес-індикатора
        composeTestRule.waitUntil(timeoutMillis = 3000) {
            composeTestRule.onAllNodesWithTag("CircularProgressIndicator").fetchSemanticsNodes().isNotEmpty()
        }

        // Очікуємо появу результату
        composeTestRule.waitUntil(timeoutMillis = 9000) {
            composeTestRule.onAllNodesWithTag("Result").fetchSemanticsNodes().isNotEmpty()
        }

        // Перевіряємо, що результат видно
        composeTestRule.onNodeWithTag("Result").assertExists().assertIsDisplayed()
    }
}