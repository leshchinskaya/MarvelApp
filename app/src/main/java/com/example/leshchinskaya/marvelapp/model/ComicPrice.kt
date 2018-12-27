package com.example.leshchinskaya.marvelapp.model

import com.google.gson.annotations.SerializedName

data class ComicPrice (
        @SerializedName("type")val type: String,
        @SerializedName("price")val price: Float
)