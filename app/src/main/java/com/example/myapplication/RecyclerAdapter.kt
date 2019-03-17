package com.example.myapplication

import android.support.v7.widget.RecyclerView
import android.content.Context

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast

class RecyclerAdapter(val items : ArrayList<GeoObject>, val context: Context, val listener: (Int) -> Unit) : RecyclerView.Adapter<GeoViewHolder>()  {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GeoViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val cellForRow = layoutInflater.inflate(R.layout.geo_cell, p0, false)

        return GeoViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: GeoViewHolder, position: Int ) {
       holder.bind(items[position], position, listener)
    }



    fun removeAt(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

}