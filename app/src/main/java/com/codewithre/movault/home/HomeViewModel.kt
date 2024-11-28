package com.codewithre.movault.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.codewithre.movault.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()
}