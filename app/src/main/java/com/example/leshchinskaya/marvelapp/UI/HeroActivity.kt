package com.example.leshchinskaya.marvelapp.UI

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.leshchinskaya.marvelapp.MarvelApplication
import com.example.leshchinskaya.marvelapp.R
import com.example.leshchinskaya.marvelapp.adapter.CardAdapter
import com.example.leshchinskaya.marvelapp.model.Character
import com.example.leshchinskaya.marvelapp.model.Comic
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_hero.*
import java.util.concurrent.TimeUnit

class HeroActivity : AppCompatActivity() {
    lateinit var hero: Character
    var comics = ArrayList<Comic>()
    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero)

        recycleView_Comics.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycleView_Comics.adapter = CardAdapter(comics, this)

        val id = intent.getIntExtra("id", 0)

        compositeDisposable
                .add(
                        Observable
                                .interval(1,20, TimeUnit.SECONDS)
                                .subscribeOn(Schedulers.io()) //поток отличный от UI
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    Log.d("Polling call starts", it.toString())
                                    MarvelApplication.comicsRepo.getComicsByCharacter(id, 0)
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribeOn(Schedulers.io())
                                            .subscribe({ result ->
                                                result.data?.results?.let { comics.addAll(it) }
                                                recycleView_Comics.adapter.notifyDataSetChanged()
                                                Log.d("Polling call ends", result.data.results?.size.toString())
                                            }, {
                                                Log.e("Error network", "something went wrong (check timeout)")
                                            })
                                }, {
                                    Log.e("Polling error", it.message)
                                })
                )

//        MarvelApplication.comicsRepo.getComicsByCharacter(id, 0)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe { result ->
//                    result.data?.results?.let { comics.addAll(it) }
//                    recycleView_Comics.adapter.notifyDataSetChanged()
//                }

        MarvelApplication.heroesRepo.getCharacter(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({result ->
                    hero = result.data.results!![0]
                    Log.d("hero", result.data.results[0].toString())
                    insertHero(hero)
                }, {error -> error.printStackTrace()})
    }

    fun insertHero(hero: Character) {
        textView_name.text = hero.name
        textView_description.text = hero.description
        Picasso.get()
                .load(hero.thumbnail!!.path + "/portrait_uncanny." + hero.thumbnail!!.extension)
                .into(imageView_hero)
    }

    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }
}