package com.app.marvelapp.ui.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.marvelapp.R
import com.app.marvelapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var charactersAdapter: CharactersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        initView()
        listenCharactersData()
    }

    private fun initView() {
        binding.run {
            charactersAdapter = CharactersAdapter()
            recyclerViewCharacters.layoutManager = GridLayoutManager(requireContext(),2,RecyclerView.VERTICAL,false)
            recyclerViewCharacters.adapter=charactersAdapter
        }
    }


    private fun listenCharactersData() {
        viewModel.getCharacters().observe(requireActivity(),{
            charactersAdapter.submitList(it)
        })
    }

}