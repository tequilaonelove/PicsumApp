package ru.test.app.picsum.core.network.model

import com.google.gson.annotations.SerializedName

data class PicsDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("author")
    val author: String,
    @SerializedName("download_url")
    val imageUrl: String,
    @SerializedName("url")
    val url: String
)