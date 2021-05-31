package com.app.marvelapp.ui.main.comics

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.marvelapp.R
import com.app.marvelapp.data.model.characters.MarvelCharactersResults
import com.app.marvelapp.databinding.FragmentComicsBinding
import com.app.marvelapp.ui.main.MainActivity
import com.app.marvelapp.utils.Constants
import com.app.marvelapp.utils.GlideUtils
import com.app.marvelapp.utils.Utils
import com.app.marvelapp.utils.Utils.toString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicsFragment(private val character: MarvelCharactersResults) : Fragment(R.layout.fragment_comics) {
    private val viewModel: ComicsViewModel by viewModels()
    private lateinit var binding: FragmentComicsBinding
    private lateinit var comicsAdapter: ComicsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentComicsBinding.bind(view)

        initView()
        initClick()
        getComics()
        listenComicsData()
    }

    private fun initClick() {
        binding.run {
            btnBack.setOnClickListener {
                (activity as MainActivity).onBackPressed()
            }
        }
    }

    private fun initView() {
        binding.run {
            comicsAdapter = ComicsAdapter(listOf())
            recyclerViewComics.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
            recyclerViewComics.adapter=comicsAdapter

            character.run {
                thumbnail.run {
                    GlideUtils.urlToImageView(requireContext(),"$path.$extension",imgCharacter)
                }
                textName.text=name
                textDescription.text=description
            }
        }
    }

    private fun getComics() {
        if (Utils.isNetworkAvailable(requireContext())){
            val currentDate = Utils.getCurrentDateTime()
            val dateString = currentDate.toString("yyyy-MM-dd")
            viewModel.getComics(character.id,Constants.COMICS_START_DATE+","+dateString)
        }
        else{
            Utils.showToast(requireContext(),getString(R.string.no_internet))
        }
    }

    private fun listenComicsData(){
        viewModel.postComics.observe(requireActivity(),{
            it?.let { comics ->
                comicsAdapter.updateComics(comics.data.results)
            }
        })
    }

}