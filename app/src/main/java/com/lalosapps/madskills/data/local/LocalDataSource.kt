package com.lalosapps.madskills.data.local

import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    val bookmarksStream: Flow<List<String>>
    suspend fun toggleBookmark(movieId: String, isBookmarked: Boolean)
}