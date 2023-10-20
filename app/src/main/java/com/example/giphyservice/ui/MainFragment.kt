package com.example.giphyservice.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.giphyservice.R
import com.example.giphyservice.data.model.Gif
import com.example.giphyservice.databinding.FragmentMainBinding
import com.example.giphyservice.ui.list.GifsAdapter

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private val getBinding get() = binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater, container, false)

        val viewModel: MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val gifsAdapter = GifsAdapter(mListener = object : GifsAdapter.OnItemClickListener {
            override fun onItemClick(gif: Gif) {
                setFragmentResult("requestKey", bundleOf("url" to gif.images.originalImage.url))

                parentFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<DetailFragment>(R.id.fragmentContainerView)
                    addToBackStack("DetailFragment")
                }
            }
        })

        getBinding.errorButton.setOnClickListener { viewModel.loadData() }

        viewModel.loadData()

        getBinding.recyclerView.apply {
            adapter = gifsAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)  // context??
        }

        viewModel.getObjectData().observe(viewLifecycleOwner) { uiState ->
            getBinding.progressBar.isVisible = uiState is UIState.Loading
            getBinding.textError.isVisible = uiState is UIState.Error
            getBinding.errorButton.isVisible = uiState is UIState.Error
            if (uiState is UIState.Success) {
                gifsAdapter.updateGifs(uiState.gifs)
            } else if (uiState is UIState.Error) {
                getBinding.textError.text = uiState.error?.message
            }
        }
        return getBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}