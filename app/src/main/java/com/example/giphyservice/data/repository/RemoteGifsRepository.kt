package com.example.giphyservice.data.repository

import com.example.giphyservice.data.GifService
import com.example.giphyservice.data.model.GifModel
import com.example.giphyservice.ui.Gif

//@Inject tells Dagger how to create instances of GifsRepository
class RemoteGifsRepository(private val gifService: GifService) : GifsRepository {

    override suspend fun getGifsData(): RepositoryResult<List<Gif>> {
        return runCatching {
            gifService.getGifs()
        }.fold(
            onSuccess = {
                RepositoryResult.Success(it.res.map {
                    it.mapToGif()
                })
            },
            onFailure = { RepositoryResult.Error(it) })
    }
}

fun GifModel.mapToGif(): Gif {
    return Gif(this.images.originalImage.url)
}