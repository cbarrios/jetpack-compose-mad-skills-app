package com.lalosapps.madskills.data.network

import com.lalosapps.madskills.data.network.api.dto.Movie
import kotlinx.coroutines.flow.Flow

interface NetworkDataSource {
    val moviesStream: Flow<List<Movie>>
}