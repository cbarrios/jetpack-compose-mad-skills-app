package com.lalosapps.madskills.data.repository.bookmarks

import kotlinx.coroutines.flow.Flow

interface BookmarksRepository {
    val bookmarksStream: Flow<List<String>>
    suspend fun toggleBookmark(movieId: String, isBookmarked: Boolean): Boolean
}