package com.example.leshchinskaya.marvelapp.network

import io.reactivex.Observable
import com.example.leshchinskaya.marvelapp.model.CharacterDataWrapper
import com.example.leshchinskaya.marvelapp.model.ComicDataWrapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.sql.Time
import java.util.concurrent.TimeUnit

interface MarvelAPI {
    @GET("v1/public/characters")
    fun getHeroes(@Query("ts") ts:String,
                  @Query("apikey") apikey: String,
                  @Query("hash") hash: String,
                  @Query("limit") limit: Int,
                  @Query("offset") offset: Int): Observable<CharacterDataWrapper>

    @GET("v1/public/characters/{characterId}")
    fun getCharacter(@Path("characterId") characterId: Int,
                     @Query("ts") ts:String,
                     @Query("apikey") apikey: String,
                     @Query("hash") hash: String): Observable<CharacterDataWrapper>

    @GET("v1/public/characters/{characterId}/comics")
    fun getComicsByCharacter(@Path("characterId") characterId: Int,
                             @Query("ts") ts:String,
                             @Query("apikey") apikey: String,
                             @Query("hash") hash: String,
                             @Query("limit") limit: Int,
                             @Query("offset") offset: Int): Observable<ComicDataWrapper>

    companion object Factory {
        fun create(): MarvelAPI {
            val okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build()
            val retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://gateway.marvel.com:443/")
                    .build()
            return retrofit.create(MarvelAPI::class.java)
        }
    }
}