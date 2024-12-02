package com.codewithre.core.data.source.remote.network

import com.codewithre.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies() : ListMovieResponse
}