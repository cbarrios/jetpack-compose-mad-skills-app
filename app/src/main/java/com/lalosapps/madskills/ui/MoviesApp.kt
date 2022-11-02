package com.lalosapps.madskills.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.lalosapps.madskills.data.network.api.dto.Movie
import com.lalosapps.madskills.domain.model.SaveableMovie

@ExperimentalLifecycleComposeApi
@Composable
fun MoviesApp(moviesViewModel: MoviesViewModel = viewModel()) {
    val moviesUiState = moviesViewModel.moviesUiState.collectAsStateWithLifecycle().value
    MoviesApp(moviesUiState = moviesUiState, onToggleBookmark = moviesViewModel::toggleBookmark)
}

@Composable
fun MoviesApp(
    moviesUiState: MoviesUiState,
    onToggleBookmark: (String, Boolean) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (moviesUiState) {
            MoviesUiState.Error -> ErrorScreen()
            MoviesUiState.Loading -> LoadingScreen()
            is MoviesUiState.Success -> MoviesScreen(
                saveableMovies = moviesUiState.saveableMovies,
                onToggleBookmark = onToggleBookmark
            )
        }
    }

}

@Composable
fun MoviesScreen(
    saveableMovies: List<SaveableMovie>,
    onToggleBookmark: (String, Boolean) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = saveableMovies, key = { it.movie.id }) {
            MovieCard(
                movie = it.movie,
                isBookmarked = it.isBookmarked,
                onToggleBookmark = {
                    onToggleBookmark(it.movie.id.toString(), !it.isBookmarked)
                }
            )
        }
    }
}

@Composable
fun MovieCard(
    movie: Movie,
    isBookmarked: Boolean,
    onToggleBookmark: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onToggleBookmark() },
        elevation = 8.dp,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(if (isBookmarked) movie.backdrop else movie.poster)
                .crossfade(true)
                .build(),
            contentDescription = "Photo",
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun ErrorScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Error")
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}


