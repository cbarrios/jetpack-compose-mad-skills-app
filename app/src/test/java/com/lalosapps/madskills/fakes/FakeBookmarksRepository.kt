package com.lalosapps.madskills.fakes

import com.lalosapps.madskills.data.repository.bookmarks.BookmarksRepository
import com.lalosapps.madskills.fakes.FakeDataSource.bookmarks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeBookmarksRepository : BookmarksRepository {

    private val _bookmarksStream = MutableStateFlow(bookmarks)
    override val bookmarksStream: Flow<List<String>> = _bookmarksStream.asStateFlow()

    override suspend fun toggleBookmark(movieId: String, isBookmarked: Boolean): Boolean {
        if (isBookmarked) {
            if (!bookmarks.contains(movieId)) {
                bookmarks = bookmarks + movieId
                _bookmarksStream.value = bookmarks
            }
        } else {
            bookmarks = bookmarks.filterNot { it == movieId }
            _bookmarksStream.value = bookmarks
        }
        return true
    }

    fun setEmptyBookmarks() {
        FakeDataSource.setEmptyBookmarks()
        _bookmarksStream.value = bookmarks
    }

    fun resetState() {
        FakeDataSource.resetBookmarks()
        _bookmarksStream.value = bookmarks
    }
}