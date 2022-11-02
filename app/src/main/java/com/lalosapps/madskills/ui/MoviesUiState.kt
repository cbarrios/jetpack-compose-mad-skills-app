package com.lalosapps.madskills.ui

import com.lalosapps.madskills.domain.model.SaveableMovie

sealed interface MoviesUiState {
    object Loading : MoviesUiState
    object Error : MoviesUiState
    data class Success(val saveableMovies: List<SaveableMovie>) : MoviesUiState
}