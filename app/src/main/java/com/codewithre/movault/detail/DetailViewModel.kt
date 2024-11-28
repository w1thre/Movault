package com.codewithre.movault.detail

import androidx.lifecycle.ViewModel
import com.codewithre.movault.core.domain.model.Movie
import com.codewithre.movault.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    fun setFavMovie(movie: Movie, newStatus: Boolean) =
        movieUseCase.setFavMovie(movie, newStatus)
}