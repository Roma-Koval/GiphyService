package com.example.giphyservice.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.giphyservice.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    companion object {
        const val URL = "url"
        fun start(context: Context, url: String?) {
            context.startActivity(
                Intent(context, SecondActivity::class.java).putExtra(URL, url)
            )
        }
    }

    private lateinit var secondBinding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(secondBinding.root)

        val url = intent.getStringExtra(URL)

        Glide.with(this).load(url).into(secondBinding.imageView)
    }
}