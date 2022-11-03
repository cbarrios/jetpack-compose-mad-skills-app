package com.lalosapps.madskills

import com.lalosapps.madskills.data.network.api.dto.Movie
import com.lalosapps.madskills.domain.model.SaveableMovie
import com.lalosapps.madskills.ui.MoviesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object FakeDataSource {

    private val sampleMovie = Movie(
        id = 1,
        title = "1",
        originalTitle = "Original 1",
        overview = "Hello 1",
        posterPath = "/gGEsBPAijhVUFoiNpgZXqRVWJt2.jpg",
        backdropPath = "/askg3SMvhqEl4OL52YuvdtY40Yb.jpg",
        releaseDate = "2022",
        originalLanguage = "en",
        popularity = 10.0,
        voteAverage = 89.0,
        voteCount = 1000,
        adult = false,
        video = false,
        genreIds = emptyList()
    )

    private val sampleSaveableMoviesBookmarked = listOf(
        SaveableMovie(
            movie = sampleMovie,
            isBookmarked = true
        )
    )
    private val sampleSaveableMoviesNotBookmarked = listOf(
        SaveableMovie(
            movie = sampleMovie,
            isBookmarked = false
        )
    )

    private val moviesUiState =
        MutableStateFlow<MoviesUiState>(MoviesUiState.Success(sampleSaveableMoviesBookmarked))
    val uiState = moviesUiState.asStateFlow()

    fun toggleMovie(id: String, isBookmarked: Boolean) {
        if (moviesUiState.value is MoviesUiState.Success) {
            val list = (moviesUiState.value as MoviesUiState.Success).saveableMovies
            moviesUiState.value =
                MoviesUiState.Success(
                    list.map {
                        if (it.movie.id.toString() == id) {
                            it.copy(isBookmarked = isBookmarked)
                        } else {
                            it
                        }
                    }
                )
        }
    }

    fun initBookmarked() {
        moviesUiState.value = MoviesUiState.Success(sampleSaveableMoviesBookmarked)
    }

    fun initNotBookmarked() {
        moviesUiState.value = MoviesUiState.Success(sampleSaveableMoviesNotBookmarked)
    }

    fun initLoadingOnly() {
        moviesUiState.value = MoviesUiState.Loading
    }

    fun initErrorOnly() {
        moviesUiState.value = MoviesUiState.Error
    }
}