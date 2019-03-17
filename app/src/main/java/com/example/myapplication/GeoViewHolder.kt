package com.example.myapplication

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.geo_cell.view.*

class GeoViewHolder (val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: GeoObject, pos: Int, listener: (Int) -> Unit) = with(itemView) {

        geoImageView.setImageResource(item.geoImageName)
        geoImageView.setOnClickListener {
            listener(item.id)
        }
    }

}