package com.codewithre.movault.detail

import android.annotation.SuppressLint
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat.getParcelableExtra
import com.bumptech.glide.Glide
import com.codewithre.movault.BuildConfig
import com.codewithre.movault.R
import com.codewithre.core.domain.model.Movie
import com.codewithre.movault.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val detailMovie = getParcelableExtra(intent, EXTRA_DATA, Movie::class.java)
        showDetailMovie(detailMovie)
    }
    
    @SuppressLint("DefaultLocale", "SetTextI18n")
    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            val urlPoster = BuildConfig.IMG_URL + detailMovie.posterPath
            val roundVote = String.format("%.1f", detailMovie.voteAverage).toDouble()
            supportActionBar?.title = detailMovie.title
            binding.contentDetailMovie.tvDetailTitle.text = detailMovie.title
            binding.contentDetailMovie.tvDetailDescription.text = detailMovie.overview
            binding.contentDetailMovie.tvDetailRating.text = roundVote.toString()
            Glide.with(this)
                .load(urlPoster)
                .into(binding.ivDetailImage)
            
            var statusFav = detailMovie.isFavorite
            setStatusFav(statusFav)
            binding.fab.setOnClickListener {
                statusFav = !statusFav
                viewModel.setFavMovie(detailMovie, statusFav)
                setStatusFav(statusFav)
            }
        }
    }
    
    private fun setStatusFav(statusFav: Boolean) {
        if (statusFav) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fav))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fav_border))
        }
    }
    
    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}