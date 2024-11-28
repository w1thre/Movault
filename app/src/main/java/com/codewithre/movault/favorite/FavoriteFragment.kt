package com.codewithre.movault.favorite

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.codewithre.movault.core.ui.MovieAdapter
import com.codewithre.movault.core.ui.ViewModelFactory
import com.codewithre.movault.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        if (activity != null) {
            val movieAdapter = MovieAdapter()
            
            viewModel.favMovie.observe(viewLifecycleOwner) { favMovie ->
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
}