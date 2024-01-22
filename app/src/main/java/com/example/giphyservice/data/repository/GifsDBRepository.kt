package com.example.giphyservice.data.repository

import com.example.giphyservice.data.GifService
import com.example.giphyservice.data.model.DataImage
import com.example.giphyservice.data.model.DataResult
import com.example.giphyservice.data.model.Gif
import com.example.giphyservice.data.model.OriginalImage
import com.example.giphyservice.data.room.database.GifDatabase
import com.example.giphyservice.data.room.entities.GifEntity
import java.time.LocalDateTime
import javax.inject.Inject

class GifsDBRepository @Inject constructor(
    private val gifService: GifService,
    private val gifDatabase: GifDatabase
) : GifsRepository {

    override suspend fun getGifsData(): RepositoryResult<Gif> {
        return runCatching {
            RepositoryResult.Success(
                DataResult(
                getAllGifs().map {
                Gif(DataImage(
                OriginalImage(it.gifUrl)
            )) }))
        }.getOrElse {
            RepositoryResult.Error(it)
        }
    }

     suspend fun getAndSaveGifs() {
        val gifs = gifService.getGifs().res
        gifDatabase.getGifDao()
            .insertGif(gifs.map {
                GifEntity(
                gifUrl = it.images.originalImage.url,
                lastUpdate = LocalDateTime.now()) })
    }

    suspend fun getAllGifs() = gifDatabase.getGifDao().getGifsFromDB()

    suspend fun deleteGifs() = gifDatabase.getGifDao().deleteAll()

    suspend fun getLastUpdate(): LocalDateTime = getAllGifs()[0].lastUpdate
}