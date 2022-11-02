package com.lalosapps.madskills.fakes

import com.lalosapps.madskills.data.network.api.dto.Movie

object FakeDataSource {

    // Movies
    val movies = listOf(
        Movie(
            id = 1,
            title = "1",
            originalTitle = "Original 1",
            overview = "Hello 1",
            posterPath = "url1",
            backdropPath = "url2",
            releaseDate = "2022",
            originalLanguage = "en",
            popularity = 10.0,
            voteAverage = 89.0,
            voteCount = 1000,
            adult = false,
            video = false,
            genreIds = emptyList()
        )
    )

    val emptyMovies = listOf<Movie>()

    // Bookmarks
    var bookmarks = movies.map { it.id.toString() }

    fun resetBookmarks() {
        bookmarks = movies.map { it.id.toString() }
    }

    fun setEmptyBookmarks() {
        bookmarks = emptyList()
    }
}