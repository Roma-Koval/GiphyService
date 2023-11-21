package com.example.giphyservice.data.repository

interface GifsRepository {
    fun getGifsData(gifCallback: GifCallback)
}