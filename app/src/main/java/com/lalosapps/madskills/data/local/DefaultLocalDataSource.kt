package com.lalosapps.madskills.data.local

import com.lalosapps.madskills.data.local.datastore.PreferencesDataStore
import kotlinx.coroutines.flow.Flow

class DefaultLocalDataSource(
    private val dataStore: PreferencesDataStore
) : LocalDataSource {

    override val bookmarksStream: Flow<List<String>>
        get() = dataStore.bookmarksStream

    override suspend fun toggleBookmark(movieId: String, isBookmarked: Boolean) {
        dataStore.toggleBookmark(movieId, isBookmarked)
    }
}