package com.app.marvelapp.ui.main.comics

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.marvelapp.R
import com.app.marvelapp.databinding.FragmentComicsBinding

class ComicsFragment : Fragment(R.layout.fragment_comics) {
    private val viewModel: ComicsViewModel by viewModels()
    private lateinit var binding: FragmentComicsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentComicsBinding.bind(view)

    }

}