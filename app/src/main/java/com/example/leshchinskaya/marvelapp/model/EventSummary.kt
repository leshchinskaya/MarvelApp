package com.example.leshchinskaya.marvelapp.model

import com.google.gson.annotations.SerializedName

data class EventSummary(
        @SerializedName("resourceURI")val resourceURI: String,
        @SerializedName("name")val name: String
)