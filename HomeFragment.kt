package com.mutlumedyasanalortam.mutlutv.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mutlumedyasanalortam.mutlutv.R
import com.mutlumedyasanalortam.mutlutv.adapters.ChannelAdapter
import com.mutlumedyasanalortam.mutlutv.adapters.MovieAdapter
import com.mutlumedyasanalortam.mutlutv.models.Channel
import com.mutlumedyasanalortam.mutlutv.models.Movie
import com.mutlumedyasanalortam.mutlutv.utils.FirebaseHelper

class HomeFragment : Fragment() {
    
    private lateinit var tvChannelsRecycler: RecyclerView
    private lateinit var moviesRecycler: RecyclerView
    private lateinit var seriesRecycler: RecyclerView
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        
        tvChannelsRecycler = view.findViewById(R.id.tv_channels_recycler)
        moviesRecycler = view.findViewById(R.id.movies_recycler)
        seriesRecycler = view.findViewById(R.id.series_recycler)
        
        setupRecyclers()
        loadData()
        
        return view
    }
    
    private fun setupRecyclers() {
        tvChannelsRecycler.layoutManager = 
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        moviesRecycler.layoutManager = 
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        seriesRecycler.layoutManager = 
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }
    
    private fun loadData() {
        // Firebase'den verileri çek
        FirebaseHelper.getChannels { channels ->
            tvChannelsRecycler.adapter = ChannelAdapter(channels)
        }
        
        FirebaseHelper.getMovies { movies ->
            moviesRecycler.adapter = MovieAdapter(movies)
        }
        
        FirebaseHelper.getSeries { series ->
            seriesRecycler.adapter = MovieAdapter(series) // Seri adapter
        }
    }
}
