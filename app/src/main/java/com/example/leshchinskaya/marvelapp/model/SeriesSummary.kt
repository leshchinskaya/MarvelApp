package com.example.leshchinskaya.marvelapp.model

import com.google.gson.annotations.SerializedName

data class SeriesSummary(
        @SerializedName("resourceURI")val resourceURI: String,
        @SerializedName("name")val name: String
)