package com.example.giphyservice.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giphyservice.data.GifService
import com.example.giphyservice.data.model.Gif
import com.example.giphyservice.data.model.DataResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "MainViewModel"

class MainViewModel : ViewModel() {
    val objectData = MutableLiveData<List<Gif>>()
    val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val gifService = retrofit.create(GifService::class.java)

    fun loadData() {
        gifService
            .getGifs()
            .enqueue(object : Callback<DataResult?> {
                override fun onResponse(call: Call<DataResult?>, response: Response<DataResult?>) {
                    val body = response.body()
                    if (body == null) {
                        Log.d(TAG, "No response")
                    } else {
                        objectData.value = body.res
                    }
                }

                override fun onFailure(call: Call<DataResult?>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

}