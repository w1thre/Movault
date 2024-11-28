package com.codewithre.movault.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.codewithre.movault.core.domain.usecase.MovieUseCase

class FavoriteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    val favMovie = movieUseCase.getFavMovie().asLiveData()
}