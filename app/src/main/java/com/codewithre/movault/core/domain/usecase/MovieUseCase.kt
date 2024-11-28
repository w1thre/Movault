package com.codewithre.movault.core.domain.usecase

import androidx.lifecycle.LiveData
import com.codewithre.movault.core.data.Resource
import com.codewithre.movault.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getFavMovie(): Flow<List<Movie>>
    fun setFavMovie(movie: Movie, state: Boolean)
}