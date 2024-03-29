package com.example.giphyservice.data

import com.example.giphyservice.data.model.DataResult
import com.example.giphyservice.data.model.GifModel
import retrofit2.http.GET

interface GifService {
    @GET("gifs/trending?api_key=ROTqe5iNDVl5ti1dUCVXDF1E19rHrHXR")
    suspend fun getGifs(): DataResult<GifModel>
}