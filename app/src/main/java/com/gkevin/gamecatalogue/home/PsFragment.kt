package com.gkevin.gamecatalogue.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gkevin.gamecatalogue.MainViewModel
import com.gkevin.gamecatalogue.core.ui.GameAdapter
import com.gkevin.gamecatalogue.databinding.FragmentPsBinding
import com.gkevin.gamecatalogue.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PsFragment : Fragment() {

    private var _binding: FragmentPsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: GameAdapter
    val viewModel: MainViewModel by sharedViewModel()

    private var gamePage = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GameAdapter {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra(DetailActivity.GAME, it)
            startActivity(intent)
        }

        binding.rvPs.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPs.adapter = adapter

        binding.rvPs.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
        viewModel.getGames(platform = MainViewModel.PS4 , page = gamePage).observe(requireActivity(), {
            binding.rvPs.visibility = View.VISIBLE
            adapter.setItem(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}