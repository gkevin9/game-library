package com.gkevin.gamecatalogue.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gkevin.gamecatalogue.core.databinding.RvListDlcBinding
import com.gkevin.gamecatalogue.core.databinding.RvListGameBinding
import com.gkevin.gamecatalogue.core.domain.model.Game

class GameDlcAdapter(val clickCallback: (Game) -> Unit): RecyclerView.Adapter<GameDlcAdapter.GameHolder>() {

    private var listGame = ArrayList<Game>()

    fun setItem(listGames: List<Game>) {
        this.listGame.clear()
        this.listGame.addAll(listGames)
        notifyDataSetChanged()
    }

    class GameHolder(private val binding: RvListDlcBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Game) {
            Glide.with(itemView.context)
                .load(item.background_image)
                .centerCrop()
                .into(binding.imgPoster)
            binding.txtTitleDlc.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
        val binding = RvListDlcBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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