package com.example.leshchinskaya.marvelapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.leshchinskaya.marvelapp.R
import com.example.leshchinskaya.marvelapp.model.Comic
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_horizontal_card.view.*

class CardAdapter(var items: List<Comic>, val context: Context): RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_horizontal_card, parent, false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.name.text = items.get(position).title
        Picasso.get()
                .load(items!!.get(position).thumbnail!!.path + "/portrait_medium." + items!!.get(position).thumbnail!!.extension)
                .into(holder.photo)
    }

    class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photo = view.imageView_photo!!
        val name = view.textView_name!!
    }
}