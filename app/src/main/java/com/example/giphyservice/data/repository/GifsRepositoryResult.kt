package com.example.giphyservice.data.repository

import com.example.giphyservice.data.model.DataResult

sealed class GifsRepositoryResult {
    data class Success(val dataResult: DataResult) : GifsRepositoryResult()
    data class Error(val error: Throwable) : GifsRepositoryResult()
}
