package ua.kpi.practical_example_25

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class MediumAppInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    /**
     * Функція для переключення на MediumApp через DisplayModeSelector
     * Вона перевіряє наявність елемента вибору режиму та натискає на "Середній рівень"
     * Потім перевіряє, чи відображаються всі необхідні поля та кнопка
     */
    private fun switchToMediumApp() {
        // Перевіряємо, що елемент DisplayModeSelector існує на екрані
        composeTestRule.onNodeWithTag("DisplayModeSelector").assertExists()
        
        // Натискаємо на текст "Середній рівень" для переходу до середнього режиму
        composeTestRule.onNodeWithText("Середній рівень").performClick()

        // Перевіряємо, що всі елементи інтерфейсу для середнього рівня відображаються
        composeTestRule.onNodeWithTag("MediumSolarRadiation").assertExists()
        composeTestRule.onNodeWithTag("MediumTemperature").assertExists()
        composeTestRule.onNodeWithTag("MediumPanelArea").assertExists()
        composeTestRule.onNodeWithTag("MediumCalculateButton").assertExists()
    }

    @Test
    fun testInputAndCalculate() {
        // Викликаємо функцію для переходу до середнього режиму
        switchToMediumApp()

        // Вводимо значення у поле сонячної радіації
        composeTestRule.onNodeWithTag("MediumSolarRadiation").performTextInput("1000")
        
        // Вводимо значення температури
        composeTestRule.onNodeWithTag("MediumTemperature").performTextInput("25")
        
        // Вводимо значення площі панелі
        composeTestRule.onNodeWithTag("MediumPanelArea").performTextInput("10")

        // Натискаємо кнопку обчислення
        composeTestRule.onNodeWithTag("MediumCalculateButton").performClick()

        // Очікуємо, поки результат з'явиться на екрані (до 9 секунд)
        composeTestRule.waitUntil(timeoutMillis = 9000) {
            composeTestRule.onAllNodesWithTag("MediumResult").fetchSemanticsNodes().isNotEmpty()
        }

        // Перевіряємо, що результат відображається
        composeTestRule.onNodeWithTag("MediumResult").assertExists().assertIsDisplayed()
    }

    @Test
    fun testEmptyFieldsDoesNothing() {
        // Викликаємо функцію для переходу до середнього режиму
        switchToMediumApp()

        // Натискаємо кнопку обчислення без введення даних
        composeTestRule.onNodeWithTag("MediumCalculateButton").performClick()

        // Перевіряємо, що результат не з'явився (кількість результатів дорівнює 0)
        composeTestRule.onAllNodesWithTag("MediumResult").assertCountEquals(0)
    }
}