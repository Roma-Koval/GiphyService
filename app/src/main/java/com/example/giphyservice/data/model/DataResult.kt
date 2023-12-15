package com.example.giphyservice.data.model

import com.google.gson.annotations.SerializedName

data class DataResult<T>(
    @SerializedName("data")
    val res: List<T>
)
