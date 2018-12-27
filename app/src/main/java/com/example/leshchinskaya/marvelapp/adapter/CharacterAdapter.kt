package com.example.leshchinskaya.marvelapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.leshchinskaya.marvelapp.model.Character
import com.example.leshchinskaya.marvelapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_character.view.*


class CharacterAdapter(var items: List<Character>?, val context: Context, val onClickListener: View.OnClickListener): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_character, parent, false)
        view.setOnClickListener(onClickListener)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.name_textView.text = items?.get(position)?.name ?: ""
        Picasso.get()
                .load(items!!.get(position).thumbnail!!.path + "/portrait_medium." + items!!.get(position).thumbnail!!.extension)
                .into(holder.photo)
    }


    class CharacterViewHolder(view: View): RecyclerView.ViewHolder(view){
        val photo = view.character_circle_imageView!!
        val name_textView = view.name_textView!!
    }
}