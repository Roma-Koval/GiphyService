package com.example.giphyservice.data.repository

import com.example.giphyservice.data.model.Gif

interface GifCallback {
    fun onSuccess(gifs: List<Gif>)

    fun onError(error: Throwable?)
}