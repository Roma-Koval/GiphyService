package com.example.giphyservice.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giphyservice.data.model.Gif
import com.example.giphyservice.data.repository.GifsRepository
import com.example.giphyservice.data.repository.CustomCallback

//The ViewModel retrieves data from the Model and exposes it to the View through observable properties or LiveData objects.
// It also communicates with the Model to update data based on user actions

class MainViewModel : ViewModel() {
    var objectData = MutableLiveData<List<Gif>>()
    private val gifsRepository = GifsRepository()
    val error = MutableLiveData<Throwable?>()
    val isLoading = MutableLiveData<Boolean>()

    fun loadData() {
        isLoading.value = true

        gifsRepository.getGifsData(object : CustomCallback{
            override fun onSuccess(gifs: List<Gif>) {
                objectData.value = gifs
                isLoading.value = false
            }

            override fun onError(error: Throwable?) {
                isLoading.value = false
                this@MainViewModel.error.value = error
            }
        })
    }
}