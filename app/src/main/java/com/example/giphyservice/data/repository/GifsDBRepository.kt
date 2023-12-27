package com.example.giphyservice.data.repository

import com.example.giphyservice.data.GifService
import com.example.giphyservice.data.room.database.GifDatabase
import com.example.giphyservice.data.room.entities.GifEntity
import javax.inject.Inject

class GifsDBRepository @Inject constructor(
    private val gifService: GifService,
    private val gifDatabase: GifDatabase
) {
    suspend fun getAndSaveGifs() {
        val gifs = gifService.getGifs().res
        gifDatabase.getGifDao()
            .insertGif(gifs.map { GifEntity(gifUrl = it.images.originalImage.url) })
    }

    suspend fun getAllGifs() = gifDatabase.getGifDao().getGifsFromDB()
}