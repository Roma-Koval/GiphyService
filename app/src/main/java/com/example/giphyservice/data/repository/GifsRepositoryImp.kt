package com.example.giphyservice.data.repository

import com.example.giphyservice.data.GifService
import com.example.giphyservice.data.model.DataResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

//@Inject tells Dagger how to create instances of GifsRepository
class GifsRepositoryImp @Inject constructor(private val gifService: GifService) : GifsRepository {
    override fun getGifsData(gifCallback: GifCallback) {
        gifService.getGifs().enqueue(object : Callback<DataResult?> {
            override fun onResponse(call: Call<DataResult?>, response: Response<DataResult?>) {
                val body = response.body()
                if (response.isSuccessful) {
                    if (body == null) {
                        gifCallback.onSuccess(listOf())
                    } else {
                        gifCallback.onSuccess(body.res)
                    }
                } else {
                    gifCallback.onError(null)
                }
            }

            override fun onFailure(call: Call<DataResult?>, t: Throwable) {
                gifCallback.onError(t)
            }
        })
    }
}