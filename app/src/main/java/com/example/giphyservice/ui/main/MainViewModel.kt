package com.example.giphyservice.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giphyservice.data.model.Gif
import com.example.giphyservice.data.repository.GifCallback
import com.example.giphyservice.data.repository.GifsRepository

// The ViewModel retrieves data from the Model and exposes it to the View through observable properties or LiveData objects.
// It also communicates with the Model to update data based on user actions
//
// The ViewModel exists from when you first request a ViewModel until the activity is finished and destroyed.
class MainViewModel : ViewModel() {

    private val gifsRepository = GifsRepository()

    private val state = MutableLiveData<UIState>()
    fun getObjectData(): LiveData<UIState> = state

    init {
        loadData()
    }

    fun loadData() {
        state.value = UIState.Loading

        gifsRepository.getGifsData(object : GifCallback {
            override fun onSuccess(gifs: List<Gif>) {
                state.value = UIState.Success(gifs)
            }

            override fun onError(error: Throwable?) {
                state.value = UIState.Error(error)
            }
        })
    }
}