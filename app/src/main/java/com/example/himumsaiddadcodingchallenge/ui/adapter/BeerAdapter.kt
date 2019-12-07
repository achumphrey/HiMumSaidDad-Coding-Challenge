package com.example.himumsaiddadcodingchallenge.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.himumsaiddadcodingchallenge.R
import com.example.himumsaiddadcodingchallenge.data.BeerModel
import com.example.himumsaiddadcodingchallenge.ui.listener.BeerClickListener
import com.example.himumsaiddadcodingchallenge.ui.viewholder.BeerViewHolder
import com.example.himumsaiddadcodingchallenge.util.inflate

class BeerAdapter constructor(private var beerList: MutableList<BeerModel>, private val listener: BeerClickListener):
    RecyclerView.Adapter<BeerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val view : View = parent.inflate(R.layout.beer_list_holder, false)
        return BeerViewHolder(view)
    }

    override fun getItemCount(): Int {
      return beerList.size
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.bindItem(beerList[position], listener)
    }

    fun updateList(newBeerList: List<BeerModel>){
        beerList.clear()
        beerList.addAll(newBeerList)
        notifyDataSetChanged()
    }
}