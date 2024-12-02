package com.codewithre.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.codewithre.core.ui.MovieAdapter
import com.codewithre.favorite.databinding.ActivityFavoriteBinding
import com.codewithre.movault.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class FavoriteActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModel()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        loadKoinModules(favModule)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }
        
        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }
        
        viewModel.favMovie.observe(this) { favMovie ->
            movieAdapter.submitList(favMovie)
            binding.viewEmpty.root.visibility =
                if (favMovie.isNotEmpty()) View.GONE else View.VISIBLE
        }
        
        with(binding.rvMovie) {
            layoutManager = GridLayoutManager(context,2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}