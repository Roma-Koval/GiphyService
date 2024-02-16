package com.example.giphyservice.data.model

import com.google.gson.annotations.SerializedName

data class GifModel(
    @SerializedName("images")
    val images: DataImage,
    @SerializedName("title")
    val title: String,
    @SerializedName("user")
    val userInfo: UserInfo?
)
