package com.lalosapps.madskills.data.network.api.dto

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("video") val video: Boolean,
    @SerializedName("genre_ids") val genreIds: List<Int>
) {
    companion object {
        private const val URL = "https://image.tmdb.org/t/p/original"
    }

    val poster: String
        get() = "$URL$posterPath"

    val backdrop: String
        get() = "$URL$backdropPath"
}
