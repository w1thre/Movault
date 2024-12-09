package com.codewithre.movault.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.codewithre.core.domain.model.Movie
import com.codewithre.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    fun setFavMovie(movie: Movie, newStatus: Boolean) =
        movieUseCase.setFavMovie(movie, newStatus)
    
    fun getFavStatus(movieId: Int) =
        movieUseCase.getFavStatus(movieId).asLiveData()
}