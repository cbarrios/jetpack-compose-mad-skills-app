package com.lalosapps.madskills

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.lalosapps.madskills.ui.MoviesApp
import org.junit.Rule
import org.junit.Test

class MoviesAppTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun moviesAreDisplayed_toggleCardAlreadyBookmarked_verifyGoesFromBackdropToPoster() {
        FakeDataSource.initBookmarked()
        composeTestRule.setContent {
            MoviesApp(
                moviesUiState = FakeDataSource.uiState.collectAsState().value,
                onToggleBookmark = { id, isBookmarked ->
                    FakeDataSource.toggleMovie(id, isBookmarked)
                }
            )
        }
        composeTestRule.onNodeWithContentDescription("backdrop").assertExists()
        composeTestRule.onNodeWithTag("card").performClick()
        composeTestRule.onNodeWithContentDescription("poster").assertExists()
    }

    @Test
    fun moviesAreDisplayed_toggleCardAlreadyBookmarked_verifyGoesFromBackdropToPoster_thenRepeat() {
        FakeDataSource.initBookmarked()
        composeTestRule.setContent {
            MoviesApp(
                moviesUiState = FakeDataSource.uiState.collectAsState().value,
                onToggleBookmark = { id, isBookmarked ->
                    FakeDataSource.toggleMovie(id, isBookmarked)
                }
            )
        }
        composeTestRule.onNodeWithContentDescription("backdrop").assertExists()
        composeTestRule.onNodeWithTag("card").performClick()
        composeTestRule.onNodeWithContentDescription("poster").assertExists()
        composeTestRule.onNodeWithTag("card").performClick()
        composeTestRule.onNodeWithContentDescription("backdrop").assertExists()
    }

    @Test
    fun moviesAreDisplayed_toggleCardNotBookmarked_verifyGoesFromPosterToBackdrop() {
        FakeDataSource.initNotBookmarked()
        composeTestRule.setContent {
            MoviesApp(
                moviesUiState = FakeDataSource.uiState.collectAsState().value,
                onToggleBookmark = { id, isBookmarked ->
                    FakeDataSource.toggleMovie(id, isBookmarked)
                }
            )
        }
        composeTestRule.onNodeWithContentDescription("poster").assertExists()
        composeTestRule.onNodeWithTag("card").performClick()
        composeTestRule.onNodeWithContentDescription("backdrop").assertExists()
    }

    @Test
    fun moviesAreDisplayed_toggleCardNotBookmarked_verifyGoesFromPosterToBackdrop_thenRepeat() {
        FakeDataSource.initNotBookmarked()
        composeTestRule.setContent {
            MoviesApp(
                moviesUiState = FakeDataSource.uiState.collectAsState().value,
                onToggleBookmark = { id, isBookmarked ->
                    FakeDataSource.toggleMovie(id, isBookmarked)
                }
            )
        }
        composeTestRule.onNodeWithContentDescription("poster").assertExists()
        composeTestRule.onNodeWithTag("card").performClick()
        composeTestRule.onNodeWithContentDescription("backdrop").assertExists()
        composeTestRule.onNodeWithTag("card").performClick()
        composeTestRule.onNodeWithContentDescription("poster").assertExists()
    }

    @Test
    fun loadingScreenIsShown() {
        FakeDataSource.initLoadingOnly()
        composeTestRule.setContent {
            MoviesApp(
                moviesUiState = FakeDataSource.uiState.collectAsState().value,
                onToggleBookmark = { id, isBookmarked ->
                    FakeDataSource.toggleMovie(id, isBookmarked)
                }
            )
        }
        composeTestRule.onNodeWithContentDescription("progress").assertExists()
    }

    @Test
    fun errorScreenIsShown() {
        FakeDataSource.initErrorOnly()
        composeTestRule.setContent {
            MoviesApp(
                moviesUiState = FakeDataSource.uiState.collectAsState().value,
                onToggleBookmark = { id, isBookmarked ->
                    FakeDataSource.toggleMovie(id, isBookmarked)
                }
            )
        }
        composeTestRule.onNodeWithText("Error").assertExists()
    }
}