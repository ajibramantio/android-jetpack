package com.dicoding.movielist.ui.favorite.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.movielist.databinding.ActivityFavoriteHomeBinding
import com.dicoding.movielist.databinding.ActivityHomeBinding

class FavoriteHomeActivity : AppCompatActivity() {

    private var _activityFavoriteHomeBinding: ActivityFavoriteHomeBinding? = null
    private val binding get() = _activityFavoriteHomeBinding

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityFavoriteHomeBinding = ActivityFavoriteHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val sectionsPagerAdapter = FavoriteSectionPagerAdapter(this, supportFragmentManager)
            binding?.viewPagerFavorite?.adapter = sectionsPagerAdapter
            binding?.tabsFavorite?.setupWithViewPager(binding?.viewPagerFavorite)

        supportActionBar?.elevation = 0f
        supportActionBar?.title = "Favorite List"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}