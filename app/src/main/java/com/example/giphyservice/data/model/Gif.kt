package com.example.giphyservice.data.model

import com.google.gson.annotations.SerializedName

data class Gif(
    @SerializedName("images")
    val images: DataImage
)
