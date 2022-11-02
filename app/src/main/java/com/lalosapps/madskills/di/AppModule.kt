package com.lalosapps.madskills.di

import android.app.Application
import com.lalosapps.madskills.data.local.DefaultLocalDataSource
import com.lalosapps.madskills.data.local.LocalDataSource
import com.lalosapps.madskills.data.local.datastore.PreferencesDataStore
import com.lalosapps.madskills.data.repository.BookmarksRepository
import com.lalosapps.madskills.data.repository.DefaultBookmarksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePreferencesDataStore(app: Application): PreferencesDataStore {
        return PreferencesDataStore(app)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(dataStore: PreferencesDataStore): LocalDataSource {
        return DefaultLocalDataSource(dataStore)
    }

    @Provides
    @Singleton
    fun provideBookmarksRepository(localDataSource: LocalDataSource): BookmarksRepository {
        return DefaultBookmarksRepository(localDataSource)
    }
}