package com.codewithre.core.data.source.local

import com.codewithre.core.data.source.local.entity.MovieEntity
import com.codewithre.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {
   
    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()
    
    fun getFavMovies(): Flow<List<MovieEntity>> = movieDao.getFavMovies()

    suspend fun insertMovie(movieEntity: List<MovieEntity>) = movieDao.insertMovie(movieEntity)
    
    fun setFavMovies(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavMovies(movie)
    }
    
    fun getFavStatus(movieId: Int): Flow<Boolean> {
        return movieDao.getFavStatus(movieId)
    }

}