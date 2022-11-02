package com.lalosapps.madskills.domain.model

import com.lalosapps.madskills.data.network.api.dto.Movie

data class SaveableMovie(
    val movie: Movie,
    val isBookmarked: Boolean
)