package com.codewithre.movault.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat.getParcelableExtra
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.codewithre.movault.BuildConfig
import com.codewithre.movault.R
import com.codewithre.core.domain.model.Movie
import com.codewithre.movault.databinding.ActivityDetailBinding
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val detailMovie = getParcelableExtra(intent, EXTRA_DATA, Movie::class.java)
        showDetailMovie(detailMovie)
        if (detailMovie != null) {
            observeFavStatus(detailMovie.id)
        } else {
            Toast.makeText(this, getString(R.string.data_tidak_ditemukan), Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun observeFavStatus(movieId: Int) {
        lifecycleScope.launch {
            viewModel.getFavStatus(movieId).observe(this@DetailActivity) { statusFav ->
                setStatusFav(statusFav)
            }
        }
    }
    
    @SuppressLint("DefaultLocale", "SetTextI18n")
    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            val urlPoster = BuildConfig.IMG_URL + detailMovie.posterPath
            supportActionBar?.title = detailMovie.title
            binding.contentDetailMovie.tvDetailTitle.text = detailMovie.title
            binding.contentDetailMovie.tvDetailDescription.text = detailMovie.overview
            binding.contentDetailMovie.tvDetailRating.text = detailMovie.voteAverage.toString()
            Glide.with(this)
                .load(urlPoster)
                .into(binding.ivDetailImage)
            
            var statusFav = detailMovie.isFavorite
            setStatusFav(statusFav)
            binding.fab.setOnClickListener {
                statusFav = !statusFav
                viewModel.setFavMovie(detailMovie, statusFav)
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