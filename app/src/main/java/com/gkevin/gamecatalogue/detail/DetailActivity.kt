package com.gkevin.gamecatalogue.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.gkevin.gamecatalogue.R
import com.gkevin.gamecatalogue.core.domain.model.Game
import com.gkevin.gamecatalogue.core.ui.GameDlcAdapter
import com.gkevin.gamecatalogue.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var adapter: GameDlcAdapter
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = GameDlcAdapter {
            viewModel.changeDetail(it)
        }

        intent.extras?.getParcelable<Game>(GAME)?.let { viewModel.insertGame(it) }

        viewModel.game.observe(this, {
            Glide.with(this)
                .load(it.background_image)
                .centerCrop()
                .into(binding.imageView)
            binding.txtTitle.text = it.name
            supportActionBar?.title = it.name
        })

        viewModel.getDlc().observe(this, {
            adapter.setItem(it)
        })

        viewModel.getDetail().observe(this, {
            binding.txtAbout.text = HtmlCompat.fromHtml(it.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
            binding.txtReleaseDate.text = it.released
            binding.txtRating.text = it.rating.toString()
        })

        binding.rvDlc.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvDlc.setHasFixedSize(true)
        binding.rvDlc.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.btnLikeDetail) {
            if (viewModel.isGameFav()) {
                viewModel.removeFavGame()
            } else {
                viewModel.insertFavGame()
            }
            invalidateOptionsMenu()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (viewModel.isGameFav()) {
            menu?.getItem(0)?.setIcon(R.drawable.ic_thumb_up)
        } else {
            menu?.getItem(0)?.setIcon(R.drawable.ic_outline_thumb_up)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    companion object {
        const val GAME = "game"
    }
}