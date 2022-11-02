package com.lalosapps.madskills.data.repository.movies

import com.lalosapps.madskills.data.network.api.dto.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    val moviesStream: Flow<List<Movie>>
}