package com.codewithre.movault.core.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codewithre.movault.BuildConfig
import com.codewithre.movault.core.domain.model.Movie
import com.codewithre.movault.databinding.ItemListMovieBinding
import com.codewithre.movault.detail.DetailActivity

class MovieAdapter : ListAdapter<Movie, MovieAdapter.ListViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ListViewHolder {
        val binding = ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }
    
    class ListViewHolder(private var binding: ItemListMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("DefaultLocale", "SetTextI18n")
        fun bind(data: Movie) {
            val urlPoster = BuildConfig.IMG_URL + data.posterPath
            val vote = data.voteAverage
            val roundVote = String.format("%.1f", vote).toDouble()
            Log.d("COY", "Ini url poster: $urlPoster")
            Glide.with(itemView.context)
                .load(urlPoster)
                .into(binding.ivItemImage)
            binding.tvItemTitle.text = data.title
            binding.tvItemSubtitle.text = roundVote.toString()
            
            binding.root.setOnClickListener {
                Log.d("COY", "Clicked to detail act")
                val context = it.context
                val intent = Intent(context, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_DATA, data)
                }
                context.startActivity(intent)
            }
        }
    }
    
    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Movie> =
            object : DiffUtil.ItemCallback<Movie>() {
                override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem == newItem
                }
                
                override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem == newItem
                }
            }
    }
}