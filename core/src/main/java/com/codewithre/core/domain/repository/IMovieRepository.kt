package com.codewithre.core.domain.repository

import com.codewithre.core.data.Resource
import com.codewithre.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    
    fun getFavMovie(): Flow<List<Movie>>
    
    fun setFavMovie(movie: Movie, state: Boolean)
    
    fun getFavStatus(movieId: Int): Flow<Boolean>
}