package com.example.giphyservice.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
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

        val textView: TextView = findViewById(R.id.textError)

        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        val errorButton: Button = findViewById(R.id.errorButton)

        val gifsAdapter =
            GifsAdapter(context = this, mListener = object : GifsAdapter.OnItemClickListener {
                override fun onItemClick(gif: Gif) {
                    SecondActivity.start(this@MainActivity, gif.images.originalImage.url)
                }
            })

        errorButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                viewModel.loadData()
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
            textView.visibility = View.GONE
            errorButton.visibility = View.GONE
        }

        viewModel.error.observe(this) { error ->
            textView.text = error?.message
            textView.visibility = View.VISIBLE
            errorButton.visibility = View.VISIBLE
        }

        viewModel.isLoading.observe(this) { isLoading ->
            progressBar.isVisible = isLoading
            textView.isVisible = false
        }
    }
}