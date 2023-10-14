package com.example.giphyservice.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giphyservice.R
import com.example.giphyservice.data.model.Gif
import com.example.giphyservice.databinding.ActivityMainBinding
import com.example.giphyservice.ui.list.GifsAdapter


const val BASE_URL = "https://api.giphy.com/v1/"

//the View observes changes in the ViewModel and updates its state accordingly
class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val viewModel: MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val gifsAdapter = GifsAdapter(mListener = object : GifsAdapter.OnItemClickListener {
            override fun onItemClick(gif: Gif) {
                SecondActivity.start(this@MainActivity, gif.images.originalImage.url)
            }
        })

        mainBinding.errorButton.setOnClickListener { viewModel.loadData() }

        viewModel.loadData()

        findViewById<RecyclerView>(R.id.recyclerView).apply {
            adapter = gifsAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }

        viewModel.getObjectData().observe(this) { uiState ->
            mainBinding.progressBar.isVisible = uiState is UIState.Loading
            mainBinding.textError.isVisible = uiState is UIState.Error
            mainBinding.errorButton.isVisible = uiState is UIState.Error
            if (uiState is UIState.Success) {
                gifsAdapter.updateGifs(uiState.gifs)
            } else if (uiState is UIState.Error) {
                mainBinding.textError.text = uiState.error?.message
            }
        }

        /*        viewModel.getError().observe(this) { error ->
                    textView.text = error?.message
                    textView.isVisible = true
                    errorButton.isVisible = true
                }

                viewModel.getIsLoading().observe(this) { isLoading ->
                    progressBar.isVisible = isLoading
                    textView.isVisible = false
                }*/
    }
}