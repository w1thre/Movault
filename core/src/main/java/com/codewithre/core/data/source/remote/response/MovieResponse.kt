package com.codewithre.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    
    @field:SerializedName("overview")
    val overview: String,
    
    @field:SerializedName("title")
    val title: String,
    
    @field:SerializedName("poster_path")
    val posterPath: String,
    
    @field:SerializedName("vote_average")
    val voteAverage: Double,
    
    @field:SerializedName("id")
    val id: Int,
    
    )