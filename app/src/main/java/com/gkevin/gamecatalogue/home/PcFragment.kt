package com.gkevin.gamecatalogue.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.gkevin.gamecatalogue.MainViewModel
import com.gkevin.gamecatalogue.R
import com.gkevin.gamecatalogue.core.ui.GameAdapter
import com.gkevin.gamecatalogue.databinding.FragmentPcBinding
import com.gkevin.gamecatalogue.detail.DetailActivity
import com.gkevin.gamecatalogue.util.Util
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PcFragment : Fragment() {

    private var binding: FragmentPcBinding? = null
    private val viewModel: MainViewModel by sharedViewModel()
    private lateinit var adapter: GameAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPcBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GameAdapter {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra(DetailActivity.GAME, it)
            startActivity(intent)
        }

        binding?.rvHome?.layoutManager = GridLayoutManager(requireContext(), Util.calculateNoOfColumns(requireContext(), 200f))
        binding?.rvHome?.adapter = adapter

        viewModel.getGames().observe(requireActivity(), {
            adapter.setItem(it)
        })

    }

}