package com.example.giphyservice.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.giphyservice.R

class SecondActivity : AppCompatActivity() {
    companion object {
        const val URL = "url"
        fun start(context: Context, url: String?) {
            context.startActivity(
                Intent(context, SecondActivity::class.java)
                    .putExtra(URL, url)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val url = intent.getStringExtra(URL)

        Glide.with(this).load(url).into(imageView)
    }
}