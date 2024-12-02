package com.codewithre.movault.di

import com.codewithre.core.domain.usecase.MovieInteractor
import com.codewithre.core.domain.usecase.MovieUseCase
import com.codewithre.movault.detail.DetailViewModel
import com.codewithre.movault.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}