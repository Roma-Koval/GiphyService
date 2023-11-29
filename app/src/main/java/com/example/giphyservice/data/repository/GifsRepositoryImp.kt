package com.example.giphyservice.data.repository

import com.example.giphyservice.data.GifService
import javax.inject.Inject

//@Inject tells Dagger how to create instances of GifsRepository
class GifsRepositoryImp @Inject constructor(private val gifService: GifService) : GifsRepository {
    override suspend fun getGifsData() = gifService.getGifs()
}