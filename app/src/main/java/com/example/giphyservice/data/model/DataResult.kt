package com.example.giphyservice.data.model

import com.google.gson.annotations.SerializedName

data class DataResult(
    @SerializedName("data")
    val res: List<Gif>
)
