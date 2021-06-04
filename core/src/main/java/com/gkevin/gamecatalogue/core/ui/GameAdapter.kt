package com.gkevin.gamecatalogue.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gkevin.gamecatalogue.core.databinding.RvListGameBinding
import com.gkevin.gamecatalogue.core.domain.model.Game

class GameAdapter(val clickCallback: (Game) -> Unit): RecyclerView.Adapter<GameAdapter.GameHolder>() {

    private var listGame = ArrayList<Game>()

    fun setItem(listGames: List<Game>) {
        this.listGame.clear()
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
        val binding = RvListGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameHolder(binding)
    }

    override fun onBindViewHolder(holder: GameHolder, position: Int) {
        holder.bind(listGame[position])
        holder.itemView.setOnClickListener {
            clickCallback(listGame[position])
        }
    }

    override fun getItemCount(): Int {
        return listGame.size
    }
}