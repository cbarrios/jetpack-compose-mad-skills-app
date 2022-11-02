package com.lalosapps.madskills.fakes

import com.lalosapps.madskills.data.network.api.dto.Movie
import com.lalosapps.madskills.data.repository.movies.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMoviesRepository : MoviesRepository {

    var emitEmpty = false

    override val moviesStream: Flow<List<Movie>> = flow {
        if (emitEmpty) {
            emit(FakeDataSource.emptyMovies)
        } else {
            emit(FakeDataSource.movies)
        }
    }

    fun resetState() {
        emitEmpty = false
    }
}