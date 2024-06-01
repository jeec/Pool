package com.jerry.mycompose

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.jerry.mycompose.navi.RallyNavHost
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupRallyNavHost() {
        composeTestRule.setContent {
            // Creates a TestNavHostController
            navController = TestNavHostController(LocalContext.current)
            // Sets a ComposeNavigator to the navController so it can navigate through composables
            navController.navigatorProvider.addNavigator(
                ComposeNavigator()
            )
            RallyNavHost(navController = navController)
        }
    }

    @Test
    fun rallyNavHost_verifyStartDestination() {
        composeTestRule
            .onNodeWithContentDescription("desc_page_1")
            .assertIsDisplayed()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals("first_page", route)
    }

    @Test
    fun rallyNavHost_clickBtn_navigateToNextPage() {
        composeTestRule
            .onNodeWithContentDescription("desc_btn_page_1")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("desc_page_2")
            .assertIsDisplayed()

    }
}