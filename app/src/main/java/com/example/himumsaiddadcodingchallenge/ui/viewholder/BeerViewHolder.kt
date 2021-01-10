package com.example.himumsaiddadcodingchallenge.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.himumsaiddadcodingchallenge.R
import com.example.himumsaiddadcodingchallenge.data.BeerModel
import com.example.himumsaiddadcodingchallenge.ui.listener.BeerClickListener
import com.example.himumsaiddadcodingchallenge.util.loadGlideImage
import kotlinx.android.synthetic.main.beer_list_holder.view.*

class BeerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindItem(beerList: BeerModel, listener: BeerClickListener, isBeerFavorite: Boolean) {

        itemView.tvBeerName.text = String.format("Name of Beer: %s", beerList.name)
        itemView.tvABV.text = String.format("Alcohol By Volume (ABV): %.2f", beerList.abv)
        itemView.imgBeer.loadGlideImage(beerList.imageUrl)
        itemView.setOnClickListener {
            listener.beerClickListener(beerList)
        }
        itemView.ivFavorite.setImageResource(
            if (isBeerFavorite) {
                R.drawable.ic_favorite
            } else {
                R.drawable.ic_favorite_with_border
            }
        )
    }
}