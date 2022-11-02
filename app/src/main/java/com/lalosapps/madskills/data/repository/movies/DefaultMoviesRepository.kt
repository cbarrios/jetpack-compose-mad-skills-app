package com.lalosapps.madskills.data.repository.movies

import com.lalosapps.madskills.data.network.NetworkDataSource
import com.lalosapps.madskills.data.network.api.dto.Movie
import kotlinx.coroutines.flow.Flow

class DefaultMoviesRepository(
    private val networkDataSource: NetworkDataSource
) : MoviesRepository {
    override val moviesStream: Flow<List<Movie>>
        get() = networkDataSource.moviesStream
}