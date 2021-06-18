package com.gkevin.gamecatalogue.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gkevin.gamecatalogue.MainViewModel
import com.gkevin.gamecatalogue.core.ui.GameAdapter
import com.gkevin.gamecatalogue.databinding.FragmentNintendoBinding
import com.gkevin.gamecatalogue.detail.DetailActivity
import com.gkevin.gamecatalogue.util.Util
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class NintendoFragment : Fragment() {

    private var _binding: FragmentNintendoBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: GameAdapter
    val viewModel: MainViewModel by sharedViewModel()

    private var gamePage = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNintendoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GameAdapter {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra(DetailActivity.GAME, it)
            startActivity(intent)
        }

        binding.rvNintendo.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNintendo.adapter = adapter

        binding.rvNintendo.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1)) {
                    gamePage += 1
                    loadGame()
                }
            }
        })

        loadGame()
    }

    private fun loadGame() {
        viewModel.getGames(MainViewModel.NSWITCH, page = gamePage).observe(requireActivity(), {
            adapter.setItem(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}