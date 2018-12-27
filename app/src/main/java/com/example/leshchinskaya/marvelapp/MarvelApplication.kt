package com.example.leshchinskaya.marvelapp

import android.app.Application
import com.example.leshchinskaya.marvelapp.model.RepoComic
import com.example.leshchinskaya.marvelapp.model.RepoHeroes
import com.example.leshchinskaya.marvelapp.network.MarvelAPI

class MarvelApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        val api = MarvelAPI.create()

        heroesRepo = RepoHeroes(api)
        comicsRepo = RepoComic(api)
    }

    companion object {
        lateinit var heroesRepo: RepoHeroes
        lateinit var comicsRepo: RepoComic
    }
}