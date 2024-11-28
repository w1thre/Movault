package com.codewithre.movault.core.data.source.remote.network

import com.codewithre.movault.core.data.source.remote.response.ListMovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies() : ListMovieResponse
}