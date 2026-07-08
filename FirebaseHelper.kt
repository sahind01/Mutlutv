package com.mutlumedyasanalortam.mutlutv.utils

import com.google.firebase.database.*
import com.mutlumedyasanalortam.mutlutv.models.Channel
import com.mutlumedyasanalortam.mutlutv.models.Movie
import kotlinx.coroutines.*

object FirebaseHelper {
    
    private val database = FirebaseDatabase.getInstance("https://sahink-5e99d-default-rtdb.firebaseio.com/")
    private val channelsRef = database.getReference("channels/tv")
    private val moviesRef = database.getReference("channels/movies")
    private val seriesRef = database.getReference("channels/series")
    private val settingsRef = database.getReference("settings")
    
    // Channels
    fun getChannels(callback: (List<Channel>) -> Unit) {
        channelsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val channels = mutableListOf<Channel>()
                snapshot.children.forEach { child ->
                    val channel = child.getValue(Channel::class.java)
                    channel?.let { channels.add(it) }
                }
                callback(channels)
            }
            
            override fun onCancelled(error: DatabaseError) {
                callback(emptyList())
            }
        })
    }
    
    // Movies
    fun getMovies(callback: (List<Movie>) -> Unit) {
        moviesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val movies = mutableListOf<Movie>()
                snapshot.children.forEach { child ->
                    val movie = child.getValue(Movie::class.java)
                    movie?.let { movies.add(it) }
                }
                callback(movies)
            }
            
            override fun onCancelled(error: DatabaseError) {
                callback(emptyList())
            }
        })
    }
    
    // Series
    fun getSeries(callback: (List<Movie>) -> Unit) {
        seriesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val series = mutableListOf<Movie>()
                snapshot.children.forEach { child ->
                    val serie = child.getValue(Movie::class.java)
                    serie?.let { series.add(it) }
                }
                callback(series)
            }
            
            override fun onCancelled(error: DatabaseError) {
                callback(emptyList())
            }
        })
    }
    
    // Settings
    fun getAdultContentStatus(callback: (Boolean) -> Unit) {
        settingsRef.child("adultContent").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val isAdultEnabled = snapshot.getValue(Boolean::class.java) ?: false
                callback(isAdultEnabled)
            }
            
            override fun onCancelled(error: DatabaseError) {
                callback(false)
            }
        })
    }
}
