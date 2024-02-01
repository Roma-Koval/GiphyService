package com.example.giphyservice.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.giphyservice.App
import com.example.giphyservice.R
import com.example.giphyservice.databinding.FragmentMainBinding
import com.example.giphyservice.ui.Gif
import com.example.giphyservice.ui.details.DetailFragment
import com.example.giphyservice.ui.main.list.GifsAdapter
import javax.inject.Inject

//the View observes changes in the ViewModel and updates its state accordingly
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    //Dagger  provide an instance of MainViewModelFactory from the graph
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //check the file for any of the @Inject property and inject
        //the correct object to them. Here it is mainViewModelFactory
        (activity?.application as App).appComponent.inject(this)

        val viewModel: MainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]

        val gifsAdapter = GifsAdapter(mListener = object : GifsAdapter.OnItemClickListener {
            override fun onItemClick(gif: Gif) {
                val detailFragment = DetailFragment.newInstance(gif.url)
                parentFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace(R.id.fragmentContainerView, detailFragment)
                    addToBackStack(DetailFragment.DetailFragment_NAME)
                }
            }
        })

        binding.errorButton.setOnClickListener { viewModel.loadData() }

        binding.recyclerView.apply {
            adapter = gifsAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
        }

        viewModel.getObjectData().observe(viewLifecycleOwner) { uiState ->
            binding.progressBar.isVisible = uiState is UIState.Loading
            binding.textError.isVisible = uiState is UIState.Error
            binding.errorButton.isVisible = uiState is UIState.Error
            if (uiState is UIState.Success<List<Gif>>) {
                gifsAdapter.updateGifs(uiState.data)
            } else if (uiState is UIState.Error) {
                binding.textError.text = uiState.error?.message
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}