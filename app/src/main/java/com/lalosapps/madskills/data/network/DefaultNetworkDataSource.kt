package com.lalosapps.madskills.data.network

import com.lalosapps.madskills.data.network.api.client.MoviesApi
import com.lalosapps.madskills.data.network.api.dto.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultNetworkDataSource(
    private val moviesApi: MoviesApi
) : NetworkDataSource {
    override val moviesStream: Flow<List<Movie>> = flow {
        try {
            val response = moviesApi.getPopularMovies()
            response.body()?.let {
                emit(it.results)
            } ?: emit(emptyList())
        } catch (e: Exception) {
            e.printStackTrace()
            emit(emptyList())
        }
    }
}