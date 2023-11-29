package com.example.giphyservice.data.repository

import com.example.giphyservice.data.model.DataResult
import retrofit2.Response

interface GifsRepository {
    suspend fun getGifsData() : Response<DataResult>
}