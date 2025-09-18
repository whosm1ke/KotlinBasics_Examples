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
     * Переключення на MediumApp через DisplayModeSelector
     */
    private fun switchToMediumApp() {
        composeTestRule.onNodeWithTag("DisplayModeSelector").assertExists()
        composeTestRule.onNodeWithText("Середній рівень").performClick()

        // Переконуємось, що поля та кнопка відображені
        composeTestRule.onNodeWithTag("MediumSolarRadiation").assertExists()
        composeTestRule.onNodeWithTag("MediumTemperature").assertExists()
        composeTestRule.onNodeWithTag("MediumPanelArea").assertExists()
        composeTestRule.onNodeWithTag("MediumCalculateButton").assertExists()
    }

    @Test
    fun testInputAndCalculate() {
        switchToMediumApp()

        // Вводимо дані
        composeTestRule.onNodeWithTag("MediumSolarRadiation").performTextInput("1000")
        composeTestRule.onNodeWithTag("MediumTemperature").performTextInput("25")
        composeTestRule.onNodeWithTag("MediumPanelArea").performTextInput("10")

        // Натискаємо кнопку
        composeTestRule.onNodeWithTag("MediumCalculateButton").performClick()

        // Очікуємо результат
        composeTestRule.waitUntil(timeoutMillis = 9000) {
            composeTestRule.onAllNodesWithTag("MediumResult").fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithTag("MediumResult").assertExists().assertIsDisplayed()
    }

    @Test
    fun testEmptyFieldsDoesNothing() {
        switchToMediumApp()

        // Натискаємо кнопку без введення даних
        composeTestRule.onNodeWithTag("MediumCalculateButton").performClick()

        // Результату не має з'являтися
        composeTestRule.onAllNodesWithTag("MediumResult").assertCountEquals(0)
    }
}