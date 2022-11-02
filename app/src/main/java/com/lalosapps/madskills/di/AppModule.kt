package com.lalosapps.madskills.di

import android.app.Application
import com.lalosapps.madskills.data.local.DefaultLocalDataSource
import com.lalosapps.madskills.data.local.LocalDataSource
import com.lalosapps.madskills.data.local.datastore.PreferencesDataStore
import com.lalosapps.madskills.data.network.DefaultNetworkDataSource
import com.lalosapps.madskills.data.network.NetworkDataSource
import com.lalosapps.madskills.data.network.api.client.MoviesApi
import com.lalosapps.madskills.data.repository.bookmarks.BookmarksRepository
import com.lalosapps.madskills.data.repository.bookmarks.DefaultBookmarksRepository
import com.lalosapps.madskills.data.repository.movies.DefaultMoviesRepository
import com.lalosapps.madskills.data.repository.movies.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    @Singleton
    fun provideMoviesApi(): MoviesApi {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkDataSource(moviesApi: MoviesApi): NetworkDataSource {
        return DefaultNetworkDataSource(moviesApi)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(networkDataSource: NetworkDataSource): MoviesRepository {
        return DefaultMoviesRepository(networkDataSource)
    }
}