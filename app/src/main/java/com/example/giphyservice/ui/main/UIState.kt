package com.example.giphyservice.ui.main

import com.example.giphyservice.data.model.Gif

sealed class UIState {
    class Success(val gifs: List<Gif>) : UIState()

    data object Loading : UIState()

    class Error(val error: Throwable?) : UIState()
}