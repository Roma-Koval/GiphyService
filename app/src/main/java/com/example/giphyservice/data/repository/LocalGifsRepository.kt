package com.example.giphyservice.data.repository

import com.example.giphyservice.data.room.dao.GifDAO
import com.example.giphyservice.data.room.entities.GifEntity
import com.example.giphyservice.ui.Gif
import java.time.Duration
import java.time.LocalDateTime

class LocalGifsRepository(private val gifDAO: GifDAO) : GifsRepository {

    override suspend fun getGifsData(): RepositoryResult<List<Gif>> {
        return runCatching {
            val localResult = getAllGifs()
            if (Duration.between(localResult[0].lastUpdate, LocalDateTime.now()) < Duration.ofDays(1)) {
                localResult
            } else {
                deleteGifs()
                listOf()
            }
        }.fold(
            onSuccess = {
                RepositoryResult.Success(it.map {
                    it.mapTo()
                })
            },
            onFailure = {
                RepositoryResult.Error(it)
            }
        )
    }

    suspend fun saveGifs(listGifs: List<Gif>) {
        gifDAO
            .insertGif(listGifs.map {
                it.mapFrom()
            })
    }

    private suspend fun getAllGifs() = gifDAO.getGifsFromDB()

    private suspend fun deleteGifs() = gifDAO.deleteAll()
}

fun GifEntity.mapTo(): Gif {
    return Gif(gifUrl)
}

fun Gif.mapFrom(): GifEntity {
    return GifEntity(gifUrl = this.url, lastUpdate = LocalDateTime.now())
}