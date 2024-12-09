package com.codewithre.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.codewithre.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM favorite_movies")
    fun getAllMovies(): Flow<List<MovieEntity>>
    
    @Query("SELECT * FROM favorite_movies where isFavorite = 1")
    fun getFavMovies(): Flow<List<MovieEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieEntity: List<MovieEntity>)
    
    @Update
    fun updateFavMovies(movie: MovieEntity)
    
    @Query("SELECT isFavorite FROM favorite_movies where id = :movieId")
    fun getFavStatus(movieId: Int): Flow<Boolean>
}