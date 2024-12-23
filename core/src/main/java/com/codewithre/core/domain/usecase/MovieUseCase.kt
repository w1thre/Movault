package com.codewithre.core.domain.usecase

import com.codewithre.core.data.Resource
import com.codewithre.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getFavMovie(): Flow<List<Movie>>
    fun setFavMovie(movie: Movie, state: Boolean)
    fun getFavStatus(movieId: Int): Flow<Boolean>
}