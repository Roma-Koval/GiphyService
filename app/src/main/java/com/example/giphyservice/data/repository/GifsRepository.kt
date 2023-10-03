package com.example.giphyservice.data.repository

import com.example.giphyservice.data.GifService
import com.example.giphyservice.data.model.DataResult
import com.example.giphyservice.ui.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "GifsRepository"

class GifsRepository {
    private val retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    private val gifService: GifService = retrofit.create(GifService::class.java)

    fun getGifsData(customCallback: CustomCallback) {
        gifService.getGifs().enqueue(object : Callback<DataResult?> {
                override fun onResponse(call: Call<DataResult?>, response: Response<DataResult?>) {
                    val body = response.body()
                    if (response.isSuccessful) {
                        if (body == null) {
                            customCallback.onSuccess(listOf())
                        } else {
                            customCallback.onSuccess(body.res)
                        }
                    } else {
                        customCallback.onError(null)
                    }
                }

            override fun onFailure(call: Call<DataResult?>, t: Throwable) {
                customCallback.onError(t)
            }
        })
    }
}