package com.example.leshchinskaya.marvelapp.model

import com.google.gson.annotations.SerializedName

data class Character (
        @SerializedName("id")val id: Int?,
        @SerializedName("name")val name: String,
        @SerializedName("description")val description: String,
        @SerializedName("modified")val modified: String,
        @SerializedName("thumbnail")val thumbnail: Image?,
        @SerializedName("resourceURI")val resourceURI: String,
        @SerializedName("comics")val comics: ComicList?,
        @SerializedName("stories")val stories: StoryList?,
        @SerializedName("events")val events: EventList?,
        @SerializedName("series")val series: SeriesList?
)