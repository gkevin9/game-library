package com.gkevin.gamecatalogue.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.gkevin.gamecatalogue.R
import com.gkevin.gamecatalogue.core.ui.GameAdapter
import com.gkevin.gamecatalogue.detail.DetailActivity
import com.gkevin.gamecatalogue.favorite.databinding.ActivityFavoriteBinding
import com.gkevin.gamecatalogue.favorite.di.favModule
import com.gkevin.gamecatalogue.util.Util
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: GameAdapter
    val viewModel: FavoriteDynamicViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favModule)

        supportActionBar?.title = getString(R.string.favorite)

        adapter = GameAdapter {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.GAME, it)
            startActivity(intent)
        }

        binding.rvFavDynamicFeature.layoutManager = GridLayoutManager(this, Util.calculateNoOfColumns(this, 200f))
        binding.rvFavDynamicFeature.adapter = adapter

        viewModel.favGame.observe(this, {
            adapter.setItem(it)
        })
    }
}