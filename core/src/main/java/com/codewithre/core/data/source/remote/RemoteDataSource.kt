package com.codewithre.core.data.source.remote

import android.util.Log
import com.codewithre.core.data.source.remote.network.ApiResponse
import com.codewithre.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class RemoteDataSource(private val apiService: com.codewithre.core.data.source.remote.network.ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        
        fun getInstance(service: com.codewithre.core.data.source.remote.network.ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance
                    ?: RemoteDataSource(service)
            }
    }
    
    fun getAllMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getNowPlayingMovies()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}