package com.codewithre.movault.core.domain.usecase

import androidx.lifecycle.LiveData
import com.codewithre.movault.core.data.Resource
import com.codewithre.movault.core.domain.model.Movie
import com.codewithre.movault.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getAllMovie() = movieRepository.getAllMovie()
    
    override fun getFavMovie() = movieRepository.getFavMovie()
    
    override fun setFavMovie(movie: Movie, state: Boolean) = movieRepository.setFavMovie(movie, state)
}