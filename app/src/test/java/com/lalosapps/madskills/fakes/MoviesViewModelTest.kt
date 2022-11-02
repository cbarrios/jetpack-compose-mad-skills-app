package com.lalosapps.madskills.fakes

import com.lalosapps.madskills.rules.MainDispatcherRule
import com.lalosapps.madskills.ui.MoviesUiState
import com.lalosapps.madskills.ui.MoviesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val fakeMoviesRepository = FakeMoviesRepository()
    private val fakeBookmarksRepository = FakeBookmarksRepository()
    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setup() {
        fakeMoviesRepository.resetState()
        fakeBookmarksRepository.resetState()
        viewModel = MoviesViewModel(
            fakeMoviesRepository,
            fakeBookmarksRepository
        )
    }

    @Test
    fun whenMoviesAndBookmarksLoaded_UiStateIsSuccess() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.moviesUiState.collect() }
        assertEquals(
            true,
            viewModel.moviesUiState.value is MoviesUiState.Success
        )
        collectJob.cancel()
    }

    @Test
    fun whenMoviesAndBookmarksLoaded_UiStateIsSuccessAndFirstMovieIsBookmarked() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.moviesUiState.collect() }
        assertEquals(
            true,
            viewModel.moviesUiState.value is MoviesUiState.Success

        )
        assertEquals(
            true,
            (viewModel.moviesUiState.value as MoviesUiState.Success).saveableMovies.first().isBookmarked
        )
        collectJob.cancel()
    }

    @Test
    fun whenMoviesEmptyAndBookmarksLoaded_UiStateIsError() = runTest {
        fakeMoviesRepository.emitEmpty = true
        viewModel = MoviesViewModel(
            fakeMoviesRepository,
            fakeBookmarksRepository
        )
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.moviesUiState.collect() }
        assertEquals(
            MoviesUiState.Error,
            viewModel.moviesUiState.value
        )
        collectJob.cancel()
    }

    @Test
    fun toggleFirstBookmarkAfterLoad_FirstBookmarkIsRemoved() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.moviesUiState.collect() }
        val first = (viewModel.moviesUiState.value as MoviesUiState.Success).saveableMovies.first()
        viewModel.toggleBookmark(first.movie.id.toString(), !first.isBookmarked)
        assertEquals(
            false,
            (viewModel.moviesUiState.value as MoviesUiState.Success).saveableMovies.first().isBookmarked
        )
        collectJob.cancel()
    }

    @Test
    fun toggleFirstBookmarkAfterLoadWithTrue_FirstBookmarkIsKept() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.moviesUiState.collect() }
        val first = (viewModel.moviesUiState.value as MoviesUiState.Success).saveableMovies.first()
        viewModel.toggleBookmark(first.movie.id.toString(), true)
        assertEquals(
            true,
            (viewModel.moviesUiState.value as MoviesUiState.Success).saveableMovies.first().isBookmarked
        )
        collectJob.cancel()
    }

    @Test
    fun toggleBookmarkAfterLoadWithInitialEmptyBookmarks_FirstBookmarkIsAdded() = runTest {
        fakeBookmarksRepository.setEmptyBookmarks()
        viewModel = MoviesViewModel(
            fakeMoviesRepository,
            fakeBookmarksRepository
        )
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.moviesUiState.collect() }
        val first = (viewModel.moviesUiState.value as MoviesUiState.Success).saveableMovies.first()
        viewModel.toggleBookmark(first.movie.id.toString(), !first.isBookmarked)
        assertEquals(
            true,
            (viewModel.moviesUiState.value as MoviesUiState.Success).saveableMovies.first().isBookmarked
        )
        collectJob.cancel()
    }
}