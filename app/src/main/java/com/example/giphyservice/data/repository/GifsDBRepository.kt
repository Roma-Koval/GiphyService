package com.example.giphyservice.data.repository

import com.example.giphyservice.data.GifService
import com.example.giphyservice.data.model.Gif
import com.example.giphyservice.data.room.database.GifDatabase
import com.example.giphyservice.data.room.entities.GifEntity
import javax.inject.Inject

class GifsDBRepository @Inject constructor(
    private val gifService: GifService, private val gifDatabase: GifDatabase
) : GifsRepository {

    override suspend fun getGifsData(): RepositoryResult<Gif> {
        return runCatching {
            // First, try to get data from the network
            val networkResult = gifService.getGifs()
            // Save data to the local database
            gifDatabase.getGifDao()
                .insertGif(networkResult.res.map { GifEntity(gifUrl = it.images.originalImage.url) })
            // Return success with the network data
            RepositoryResult.Success(networkResult)
        }.getOrElse {
            RepositoryResult.Error(it)
        }
    }

    suspend fun getAndSaveGifs() {
        val gifs = gifService.getGifs().res
        gifDatabase.getGifDao()
            .insertGif(gifs.map { GifEntity(gifUrl = it.images.originalImage.url) })
    }

    suspend fun getAllGifs() = gifDatabase.getGifDao().getGifsFromDB()
}