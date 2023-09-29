package com.example.giphyservice.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giphyservice.data.model.Gif
import com.example.giphyservice.data.repository.GifsRepository

//The ViewModel retrieves data from the Model and exposes it to the View through observable properties or LiveData objects.
// It also communicates with the Model to update data based on user actions

class MainViewModel : ViewModel() {
    var objectData = MutableLiveData<List<Gif>>()
    private val gifsRepository = GifsRepository()
/*    val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val gifService = retrofit.create(GifService::class.java)*/

    fun loadData() {
        objectData = gifsRepository.getGifsData(objectData)
      /*  gifService
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
            })*/
    }
}