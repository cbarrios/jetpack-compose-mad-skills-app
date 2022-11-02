package com.lalosapps.madskills.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "bookmarks")

class PreferencesDataStore(private val context: Context) {

    val bookmarksStream: Flow<List<String>> = context.dataStore.data.map {
        it.asMap().keys.map { key -> key.name }
    }

    suspend fun toggleBookmark(movieId: String, isBookmarked: Boolean) {
        val key = booleanPreferencesKey(movieId)
        context.dataStore.edit { preferences ->
            if (isBookmarked) {
                preferences[key] = true
            } else {
                preferences.remove(key)
            }
        }
    }
}