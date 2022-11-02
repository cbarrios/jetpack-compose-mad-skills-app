package com.lalosapps.madskills.data.network.api.client

import com.lalosapps.madskills.data.network.api.dto.MoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    companion object {
        private const val API_KEY = "8aae16c3511197eb0ec599a5f1663d2c"
    }

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("region") region: String = "US",
    ): Response<MoviesDto>
}