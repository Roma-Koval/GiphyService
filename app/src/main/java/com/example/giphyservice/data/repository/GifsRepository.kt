package com.example.giphyservice.data.repository

interface GifsRepository {
    suspend fun getGifsData() : GifsRepositoryResult
}