package com.codewithre.movault.core.data.source.local

import androidx.lifecycle.LiveData
import com.codewithre.movault.core.data.source.local.entity.MovieEntity
import com.codewithre.movault.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val movieDao: MovieDao) {
    companion object {
        private val instance: LocalDataSource? = null
        
        fun getInstance(movieDao: MovieDao) : LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(movieDao)
            }
    }
    
    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()
    
    fun getFavMovies(): Flow<List<MovieEntity>> = movieDao.getFavMovies()

    suspend fun insertMovie(movieEntity: List<MovieEntity>) = movieDao.insertMovie(movieEntity)
    
    fun setFavMovies(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavMovies(movie)
    }

}