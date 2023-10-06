package com.example.giphyservice.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giphyservice.R
import com.example.giphyservice.data.model.Gif
import com.example.giphyservice.ui.list.GifsAdapter


const val BASE_URL = "https://api.giphy.com/v1/"

//the View observes changes in the ViewModel and updates its state accordingly
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val textView: TextView = findViewById(R.id.textError)

        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        val errorButton: Button = findViewById(R.id.errorButton)

        val gifsAdapter =
            GifsAdapter(context = this, mListener = object : GifsAdapter.OnItemClickListener {
                override fun onItemClick(gif: Gif) {
                    SecondActivity.start(this@MainActivity, gif.images.originalImage.url)
                }
            })

        errorButton.setOnClickListener { viewModel.loadData() }

        viewModel.loadData()

        findViewById<RecyclerView>(R.id.recyclerView).apply {
            adapter = gifsAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }

        viewModel.getObjectData().observe(this) { gifs ->
            gifsAdapter.updateGifs(gifs)
            textView.isVisible = false
            errorButton.isVisible = false
        }

        viewModel.getError().observe(this) { error ->
            textView.text = error?.message
            textView.isVisible = true
            errorButton.isVisible = true
        }

        viewModel.getIsLoading().observe(this) { isLoading ->
            progressBar.isVisible = isLoading
            textView.isVisible = false
        }
    }
}