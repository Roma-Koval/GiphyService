package com.example.giphyservice.data.repository

import com.example.giphyservice.ui.Gif

interface GifsRepository {
    suspend fun getGifsData() : RepositoryResult<List<Gif>>
}