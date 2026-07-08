package com.mutlumedyasanalortam.mutlutv.activities

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.mutlumedyasanalortam.mutlutv.R

class VideoPlayerActivity : AppCompatActivity() {
    
    private lateinit var playerView: PlayerView
    private lateinit var exoPlayer: ExoPlayer
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        
        // Tam ekran modu
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        
        // Immersive Mode
        hideSystemUI()
        
        playerView = findViewById(R.id.player_view)
        
        // ExoPlayer kurulumu
        exoPlayer = ExoPlayer.Builder(this).build()
        playerView.player = exoPlayer
        
        // Video URL'sini al
        val videoUrl = intent.getStringExtra("video_url") ?: ""
        val mediaItem = MediaItem.fromUri(videoUrl)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
    }
    
    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            or View.SYSTEM_UI_FLAG_FULLSCREEN
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        )
    }
    
    override fun onStop() {
        super.onStop()
        exoPlayer.release()
    }
}
