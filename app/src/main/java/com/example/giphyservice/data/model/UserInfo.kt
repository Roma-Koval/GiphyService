package com.example.giphyservice.data.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("display_name")
    val name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("is_verified")
    val isVerified: Boolean
)
