package com.codewithre.core.data

import com.codewithre.core.data.source.local.LocalDataSource
import com.codewithre.core.data.source.remote.RemoteDataSource
import com.codewithre.core.data.source.remote.network.ApiResponse
import com.codewithre.core.data.source.remote.response.MovieResponse
import com.codewithre.core.domain.model.Movie
import com.codewithre.core.domain.repository.IMovieRepository
import com.codewithre.core.utils.AppExecutors
import com.codewithre.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {
    
    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                 return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                 }
            }
            
            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()
            
            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertMovie(movieList)
            }
            
            override fun shouldFetch(data: List<Movie>?): Boolean =
                data.isNullOrEmpty()
            
        }.asFlow()
    
    override fun getFavMovie(): Flow<List<Movie>> {
        return localDataSource.getFavMovies().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }
    
    override fun setFavMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavMovies(movieEntity, state)}
    }
    
    override fun getFavStatus(movieId: Int): Flow<Boolean> = localDataSource.getFavStatus(movieId)
}