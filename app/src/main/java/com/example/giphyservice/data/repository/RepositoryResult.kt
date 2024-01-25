package com.example.giphyservice.data.repository

import com.example.giphyservice.ui.main.UIState

sealed class RepositoryResult<out T : Any> {
    data class Success<T : Any>(val dataResult: T) : RepositoryResult<T>()
    data class Error(val error: Throwable) : RepositoryResult<Nothing>()

    fun mapRepositoryToUIState(): UIState<T> {
        return when (this) {
            is Success -> UIState.Success(dataResult)
            is Error -> UIState.Error(error)
        }
    }
}
