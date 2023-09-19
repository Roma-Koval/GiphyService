package com.example.giphyservice

import retrofit2.http.GET

interface DataService {
    @GET("gifs/trending?api_key=ROTqe5iNDVl5ti1dUCVXDF1E19rHrHXR")
    fun getGifs(): retrofit2.Call<DataResult>
}