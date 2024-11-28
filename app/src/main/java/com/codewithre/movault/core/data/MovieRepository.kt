package com.codewithre.movault.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.codewithre.movault.core.data.source.local.LocalDataSource
import com.codewithre.movault.core.data.source.remote.RemoteDataSource
import com.codewithre.movault.core.data.source.remote.network.ApiResponse
import com.codewithre.movault.core.data.source.remote.response.MovieResponse
import com.codewithre.movault.core.domain.model.Movie
import com.codewithre.movault.core.domain.repository.IMovieRepository
import com.codewithre.movault.core.utils.AppExecutors
import com.codewithre.movault.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {
    
    companion object {
        @Volatile
        private var instance: MovieRepository? = null
        
        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localData, appExecutors)
            }
    }
    
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
    
}