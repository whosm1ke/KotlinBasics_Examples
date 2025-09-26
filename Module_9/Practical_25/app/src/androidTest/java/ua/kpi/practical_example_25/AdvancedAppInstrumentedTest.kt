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
        // Чекаємо, поки з'явиться селектор режимів відображення (DisplayModeSelector)
        composeTestRule.onNodeWithTag("DisplayModeSelector").assertExists()

        // Клікаємо на кнопку "Просунутий рівень", щоб перейти до інтерфейсу AdvancedApp
        composeTestRule.onNodeWithText("Просунутий рівень").performClick()

        // Переконуємось, що елементи AdvancedApp відображаються на екрані
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
        // Перемикаємося на інтерфейс AdvancedApp
        switchToAdvancedApp()

        // Вводимо значення в поле сонячної радіації (SolarRadiation)
        composeTestRule.onNodeWithTag("SolarRadiation").performTextInput("1000")

        // Вводимо значення температури (Temperature)
        composeTestRule.onNodeWithTag("Temperature").performTextInput("25")

        // Вводимо значення площі панелі (PanelArea)
        composeTestRule.onNodeWithTag("PanelArea").performTextInput("10")

        // Натискаємо кнопку розрахунку
        composeTestRule.onNodeWithTag("CalculateButton").performClick()

        // Очікуємо з'явлення прогрес-індикатора (CircularProgressIndicator)
        composeTestRule.waitUntil(timeoutMillis = 3000) {
            composeTestRule.onAllNodesWithTag("CircularProgressIndicator").fetchSemanticsNodes().isNotEmpty()
        }

        // Очікуємо з'явлення результату обчислення
        composeTestRule.waitUntil(timeoutMillis = 9000) {
            composeTestRule.onAllNodesWithTag("Result").fetchSemanticsNodes().isNotEmpty()
        }

        // Перевіряємо, що результат відображається на екрані
        composeTestRule.onNodeWithTag("Result").assertExists().assertIsDisplayed()
    }

    /**
     * Тест 2: Використання мокованих значень за замовчуванням
     */
    @Test
    fun testDefaultMockValuesWithTags() {
        // Перемикаємося на інтерфейс AdvancedApp
        switchToAdvancedApp()

        // Натискаємо кнопку розрахунку без введення даних (використовуються моковані значення)
        composeTestRule.onNodeWithTag("CalculateButton").performClick()

        // Очікуємо з'явлення результату обчислення
        composeTestRule.waitUntil(timeoutMillis = 9000) {
            composeTestRule.onAllNodesWithTag("Result").fetchSemanticsNodes().isNotEmpty()
        }

        // Перевіряємо, що результат відображається на екрані
        composeTestRule.onNodeWithTag("Result").assertExists().assertIsDisplayed()
    }

    /**
     * Тест 3: Порожні поля – використовуються моковані значення
     */
    @Test
    fun testEmptyFieldsUseDefaultValues() {
        // Перемикаємося на інтерфейс AdvancedApp
        switchToAdvancedApp()

        // Натискаємо кнопку розрахунку без введення даних (використовуються моковані значення)
        composeTestRule.onNodeWithTag("CalculateButton").performClick()

        // Очікуємо з'явлення прогрес-індикатора
        composeTestRule.waitUntil(timeoutMillis = 3000) {
            composeTestRule.onAllNodesWithTag("CircularProgressIndicator").fetchSemanticsNodes().isNotEmpty()
        }

        // Очікуємо з'явлення результату обчислення
        composeTestRule.waitUntil(timeoutMillis = 9000) {
            composeTestRule.onAllNodesWithTag("Result").fetchSemanticsNodes().isNotEmpty()
        }

        // Перевіряємо, що результат відображається на екрані
        composeTestRule.onNodeWithTag("Result").assertExists().assertIsDisplayed()
    }
}