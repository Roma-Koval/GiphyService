package com.example.giphyservice.ui.main

sealed class UIState<out T : Any> {
    class Success<T : Any>(val gifs: List<T>) : UIState<T>()

    data object Loading : UIState<Nothing>()

    class Error(val error: Throwable?) : UIState<Nothing>()
}