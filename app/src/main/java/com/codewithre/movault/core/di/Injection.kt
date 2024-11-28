package com.codewithre.movault.core.di

import android.content.Context
import com.codewithre.movault.core.data.MovieRepository
import com.codewithre.movault.core.data.source.local.LocalDataSource
import com.codewithre.movault.core.data.source.local.room.MovieDatabase
import com.codewithre.movault.core.data.source.remote.RemoteDataSource
import com.codewithre.movault.core.data.source.remote.network.ApiConfig
import com.codewithre.movault.core.domain.repository.IMovieRepository
import com.codewithre.movault.core.domain.usecase.MovieInteractor
import com.codewithre.movault.core.domain.usecase.MovieUseCase
import com.codewithre.movault.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IMovieRepository {
        val database = MovieDatabase.getInstance(context)
        
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.getApiService())
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()
        
        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
    
    fun provideMovieUseCase(context: Context): MovieUseCase {
        val repository = provideRepository(context)
        return MovieInteractor(repository)
    }
}