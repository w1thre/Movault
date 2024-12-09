package com.codewithre.core.domain.usecase

import com.codewithre.core.domain.model.Movie
import com.codewithre.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getAllMovie() = movieRepository.getAllMovie()
    
    override fun getFavMovie() = movieRepository.getFavMovie()
    
    override fun setFavMovie(movie: Movie, state: Boolean) = movieRepository.setFavMovie(movie, state)
    
    override fun getFavStatus(movieId: Int): Flow<Boolean> = movieRepository.getFavStatus(movieId)
}