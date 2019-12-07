package com.example.himumsaiddadcodingchallenge.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.himumsaiddadcodingchallenge.data.BeerModel
import com.example.himumsaiddadcodingchallenge.ui.listener.BeerClickListener
import com.example.himumsaiddadcodingchallenge.util.loadGlideImage
import kotlinx.android.synthetic.main.beer_list_holder.view.*

class BeerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bindItem(beerList:BeerModel, listener: BeerClickListener){
        itemView.tvBeerName.text = "Name of Beer: " + beerList.name
        itemView.tvABV .text = String.format("Alcohol By Volume (ABV): %.2f", beerList.abv)
        itemView.imgBeer.loadGlideImage(beerList.imageUrl)
        itemView.setOnClickListener {
            listener.beerClickListener(beerList)
        }
    }
}