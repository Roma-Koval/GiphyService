package com.example.giphyservice.data.repository

import com.example.giphyservice.data.model.DataResult
import com.example.giphyservice.ui.main.UIState

sealed class RepositoryResult<out T> {
    data class Success<T>(val dataResult: DataResult<T>) : RepositoryResult<T>()
    data class Error(val error: Throwable) : RepositoryResult<Nothing>()

    fun mapRepositoryToUIState(): UIState {
        return when (this) {
            is Success -> UIState.Success(dataResult.res)
            is Error -> UIState.Error(error)
        }
    }
}
