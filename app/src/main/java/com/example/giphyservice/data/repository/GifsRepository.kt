package com.example.giphyservice.data.repository

import com.example.giphyservice.data.model.Gif

interface GifsRepository {
    suspend fun getGifsData() : RepositoryResult<Gif>
}