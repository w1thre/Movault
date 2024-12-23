package com.codewithre.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.codewithre.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favMovie = movieUseCase.getFavMovie().asLiveData()
}