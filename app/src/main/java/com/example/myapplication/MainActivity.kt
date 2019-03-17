package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.v7.widget.RecyclerView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    val geoArray: ArrayList<GeoObject> = ArrayList()
    private var geoAdapter: RecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addGeolocations()

        // Creates a vertical Layout Manager
        geoRecyleView.layoutManager = LinearLayoutManager(this)
        geoAdapter = RecyclerAdapter(geoArray, this, listener = {
            for( item in geoArray) {
                if (item.id == it) {
                    Toast.makeText(this, "Country name is " + item.geoName, Toast.LENGTH_SHORT).show()
                }
            }
        })

        // Access the RecyclerView Adapter and load the data into it
        geoRecyleView.adapter = geoAdapter

        initSwipe()

    }

    private fun addGeolocations() {
        geoArray.add(GeoObject(0,"denmark",R.drawable.img1_yes_denmark,true))
        geoArray.add(GeoObject(1,"canada",R.drawable.img2_no_canada,false))
        geoArray.add(GeoObject(2,"bangladesh",R.drawable.img3_no_bangladesh,false))
        geoArray.add(GeoObject(3 ,"kazachstan",R.drawable.img4_yes_kazachstan,true))
        geoArray.add(GeoObject(4,"poland",R.drawable.img6_yes_poland,true))
        geoArray.add(GeoObject(5,"malta",R.drawable.img7_yes_malta,true))
        geoArray.add(GeoObject(6,"thailand",R.drawable.img8_no_thailand,false))

    }

    private fun checkIsEurope(direction: Int, position: Int) {
        if(direction == 1) {
            // swiped to left
            if(geoArray[position].isEurope) {
                Toast.makeText(this, "Correct" , Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Incorrect" , Toast.LENGTH_SHORT).show()
            }
        } else {
            // swiped to right
            if(!geoArray[position].isEurope) {
                Toast.makeText(this, "Incorrect" , Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this, "Correct" , Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun initSwipe() {
        val simpleItemTouchCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    if (direction == ItemTouchHelper.LEFT) {
                        checkIsEurope(1, position)
                        geoAdapter!!.removeAt(position)
                    } else {
                        checkIsEurope(2, position)
                        geoAdapter!!.removeAt(position)
                    }

                }

            }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(geoRecyleView)
    }

}
