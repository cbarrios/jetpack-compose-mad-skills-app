package com.lalosapps.madskills.data.repository.bookmarks

import com.lalosapps.madskills.data.local.LocalDataSource
import kotlinx.coroutines.flow.Flow

class DefaultBookmarksRepository(
    private val localDataSource: LocalDataSource
) : BookmarksRepository {
    override val bookmarksStream: Flow<List<String>>
        get() = localDataSource.bookmarksStream

    override suspend fun toggleBookmark(movieId: String, isBookmarked: Boolean): Boolean {
        return try {
            localDataSource.toggleBookmark(movieId, isBookmarked)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}