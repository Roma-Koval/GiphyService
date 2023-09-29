package com.example.giphyservice.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giphyservice.R
import com.example.giphyservice.data.model.Gif
import com.example.giphyservice.ui.list.GifsAdapter

const val BASE_URL = "https://api.giphy.com/v1/"

//the View observes changes in the ViewModel and updates its state accordingly
class MainActivity : AppCompatActivity() {
    private var viewModel: MainViewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gifsAdapter = GifsAdapter(context = this, mListener = object : GifsAdapter.OnItemClickListener {
            override fun onItemClick(gif: Gif) {
                SecondActivity.start(this@MainActivity, gif.images.originalImage.url)
            }
        })

        viewModel.loadData()
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            adapter = gifsAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }
        viewModel.objectData.observe(this) { gifs ->
            gifsAdapter.updateGifs(gifs)
        }
    }
}