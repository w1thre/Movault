package com.codewithre.movault.core.utils

import com.codewithre.movault.core.data.source.local.entity.MovieEntity
import com.codewithre.movault.core.data.source.remote.response.MovieResponse
import com.codewithre.movault.core.domain.model.Movie

object DataMapper {
    fun mapResponseToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }
    
    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                isFavorite = it.isFavorite
            )
        }
    
    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        title = input.title,
        overview = input.overview,
        posterPath = input.posterPath.toString(),
        voteAverage = input.voteAverage,
        isFavorite = input.isFavorite
    )
}