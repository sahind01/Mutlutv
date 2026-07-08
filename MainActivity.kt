package com.mutlumedyasanalortam.mutlutv.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mutlumedyasanalortam.mutlutv.R
import com.mutlumedyasanalortam.mutlutv.fragments.HomeFragment
import com.mutlumedyasanalortam.mutlutv.fragments.LiveTVFragment
import com.mutlumedyasanalortam.mutlutv.fragments.MoviesFragment
import com.mutlumedyasanalortam.mutlutv.fragments.SeriesFragment
import com.mutlumedyasanalortam.mutlutv.fragments.SettingsFragment

class MainActivity : AppCompatActivity() {
    
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var navView: NavigationView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        drawerLayout = findViewById(R.id.drawer_layout)
        bottomNav = findViewById(R.id.bottom_navigation)
        navView = findViewById(R.id.nav_view)
        
        // Varsayılan fragment
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
            bottomNav.selectedItemId = R.id.nav_home
        }
        
        // Bottom Navigation listener
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(HomeFragment())
                R.id.nav_live_tv -> loadFragment(LiveTVFragment())
                R.id.nav_movies -> loadFragment(MoviesFragment())
                R.id.nav_series -> loadFragment(SeriesFragment())
                R.id.nav_settings -> loadFragment(SettingsFragment())
            }
            true
        }
        
        // Navigation Drawer listener
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_favorites -> {
                    // Favoriler sayfası
                }
                R.id.nav_recent -> {
                    // En Son İzlenenler
                }
                R.id.nav_adult -> {
                    // Adult içerik
                }
                R.id.nav_about -> {
                    // Hakkımızda
                }
                R.id.nav_privacy -> {
                    // Gizlilik Politikası
                }
                R.id.nav_contact -> {
                    // Biz Kimiz
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }
    
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
    
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
