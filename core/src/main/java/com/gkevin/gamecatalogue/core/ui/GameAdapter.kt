package com.gkevin.gamecatalogue.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gkevin.gamecatalogue.core.databinding.RvListGameBinding
import com.gkevin.gamecatalogue.core.databinding.RvLoadGameBinding
import com.gkevin.gamecatalogue.core.domain.model.Game

class GameAdapter(val clickCallback: (Game) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listGame = ArrayList<Game>()

    fun setItem(listGames: List<Game>) {
        this.listGame.addAll(listGames)
        notifyDataSetChanged()
    }

    class GameHolder(private val binding: RvListGameBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Game) {
            binding.txtTitle.text = item.name
            binding.txtRating.text = item.rating.toString()
            Glide.with(itemView.context)
                .load(item.background_image)
                .centerCrop()
                .into(binding.imgViewPoster)
        }
    }

    class LoadingHolder(private val binding: RvLoadGameBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == GAMETYPE) {
            val binding = RvListGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            GameHolder(binding)
        } else {
            val binding = RvLoadGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            LoadingHolder(binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == GAMETYPE) {
            (holder as GameHolder).bind(listGame[position])
            holder.itemView.setOnClickListener {
                clickCallback(listGame[position])
            }
        } else {
            (holder as LoadingHolder)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == listGame.size - 1) {
            LOADINGTYPE
        } else {
            GAMETYPE
        }
    }

    override fun getItemCount(): Int {
        return listGame.size
    }

    companion object {
        private val GAMETYPE = 1
        private val LOADINGTYPE = 2
    }
}