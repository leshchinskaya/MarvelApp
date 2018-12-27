package com.example.leshchinskaya.marvelapp.UI

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.leshchinskaya.marvelapp.MarvelApplication
import com.example.leshchinskaya.marvelapp.R
import com.example.leshchinskaya.marvelapp.adapter.CharacterAdapter
import com.example.leshchinskaya.marvelapp.model.Character
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_characters.*


class CharacterActivity : AppCompatActivity(), View.OnClickListener{
    var MarvelData = ArrayList<Character>()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var offset = 0
    var loading = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        offset = 0
        linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        characters_recycle_view.layoutManager = linearLayoutManager

        var adapter = CharacterAdapter(MarvelData, this, this)
        characters_recycle_view.adapter = adapter

        MarvelApplication.heroesRepo.getHeroes(offset)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    result.data.results?.let { MarvelData.addAll(it) }
                    characters_recycle_view.adapter.notifyDataSetChanged()
                }, { error -> error.printStackTrace() })

        characters_recycle_view.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                recyclerViewScrollEvent()
            }
        })
    }

    fun recyclerViewScrollEvent(){
        var visibleItemCount = linearLayoutManager.childCount
        var totalItemCount = linearLayoutManager.itemCount
        var firstVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()

        if(!loading)
            if((visibleItemCount + firstVisibleItems) >= totalItemCount) {
                loading = true
                loadMoreCharacter()
            }
    }

    fun loadMoreCharacter(){
        offset+= 1
        MarvelApplication.heroesRepo.getHeroes(offset)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    result.data.results?.let { MarvelData.addAll(it) }
                    loading = false
                    characters_recycle_view.adapter.notifyDataSetChanged()
                }, { error -> error.printStackTrace() })
    }

    fun startHeroActivity(id: Int) {
        val intentHero = Intent(this, HeroActivity::class.java)
        intentHero.putExtra("id", id)
        startActivity(intentHero)
    }

    override fun onClick(v: View?) {
        val itemPosition = characters_recycle_view.getChildLayoutPosition(v)
        val item = MarvelData.get(itemPosition)

        startHeroActivity(item.id!!)
    }
}

