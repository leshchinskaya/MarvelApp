package com.example.leshchinskaya.marvelapp.UI

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.leshchinskaya.marvelapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_heroes.setOnClickListener({openHeroesActivity()})
    }

    fun openHeroesActivity() {
        val intent = Intent(this, CharacterActivity::class.java)
        startActivity(intent)
    }
}
