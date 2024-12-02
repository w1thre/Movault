package com.codewithre.core.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codewithre.core.databinding.ItemListMovieBinding
import com.codewithre.core.domain.model.Movie
import com.codewithre.core.BuildConfig

class MovieAdapter : ListAdapter<Movie, MovieAdapter.ListViewHolder>(DIFF_CALLBACK) {
    
    var onItemClick: ((Movie) -> Unit)? = null
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ListViewHolder {
        val binding = ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }
    
    inner class ListViewHolder(private var binding: ItemListMovieBinding) : RecyclerView.ViewHolder(binding.root) {
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
        }
        
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
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