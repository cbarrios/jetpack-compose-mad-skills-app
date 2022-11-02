package com.lalosapps.madskills.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lalosapps.madskills.data.repository.bookmarks.BookmarksRepository
import com.lalosapps.madskills.data.repository.movies.MoviesRepository
import com.lalosapps.madskills.domain.model.SaveableMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val bookmarksRepository: BookmarksRepository
) : ViewModel() {

    private val combinedData: Flow<List<SaveableMovie>> =
        combine(
            moviesRepository.moviesStream,
            bookmarksRepository.bookmarksStream
        ) { movies, bookmarks ->
            movies.map { movie ->
                val isBookmarked = bookmarks.contains(movie.id.toString())
                SaveableMovie(movie, isBookmarked)
            }
        }

    val moviesUiState: StateFlow<MoviesUiState> =
        combinedData.map {
            if (it.isEmpty()) MoviesUiState.Error
            else MoviesUiState.Success(it)
        }.stateIn(
            scope = viewModelScope,
            initialValue = MoviesUiState.Loading,
            started = SharingStarted.WhileSubscribed(5000)
        )

    fun toggleBookmark(movieId: String, isBookmarked: Boolean) {
        viewModelScope.launch {
            bookmarksRepository.toggleBookmark(movieId, isBookmarked)
        }
    }
}