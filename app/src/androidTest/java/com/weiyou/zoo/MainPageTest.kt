package com.weiyou.zoo

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainPageTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun chantingApp_displaysBottomNavigation() {
        // Launch the ChantingApp composable
        composeTestRule.setContent {
            ChantingApp()
        }

        // Check if the BottomNavigation is displayed
        composeTestRule.onNodeWithText("Home").assertIsDisplayed()
        composeTestRule.onNodeWithTag("test_tag_for_lazy_column").assertExists()
        composeTestRule.onNodeWithText("Accounts").assertIsDisplayed()
    }

    // Add more tests as needed for specific functionality
    @Test
    fun clickAccounts_NavigateToAccountsScreen() {
        composeTestRule.setContent {
            ChantingApp()
        }

        // Click on the "Accounts" BottomNavigationItem
        composeTestRule.onNodeWithContentDescription("Accounts").performClick()

        // Verify that the AccountsScreen is displayed
        composeTestRule.onNodeWithText("AccountsScreen").assertExists() // Adjust this to match your actual screen content
        // You can also verify other elements or behaviors specific to the AccountsScreen
    }

    // Add more tests as needed for specific functionality
    @Test
    fun clickHome_NavigateToHomeScreen() {
        // Launch the ChantingApp composable
        composeTestRule.setContent {
            ChantingApp()
        }

        composeTestRule.onNodeWithContentDescription("Home").performClick()

        composeTestRule.onNodeWithText("HomeScreen").assertExists()
        composeTestRule.onNodeWithTag("test_tag_for_lazy_column").assertExists()
    }

}