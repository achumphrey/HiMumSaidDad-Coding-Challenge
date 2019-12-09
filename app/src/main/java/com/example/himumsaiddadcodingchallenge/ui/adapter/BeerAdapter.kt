package com.example.himumsaiddadcodingchallenge.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.himumsaiddadcodingchallenge.R
import com.example.himumsaiddadcodingchallenge.data.BeerModel
import com.example.himumsaiddadcodingchallenge.ui.listener.BeerClickListener
import com.example.himumsaiddadcodingchallenge.ui.viewholder.BeerViewHolder
import com.example.himumsaiddadcodingchallenge.util.inflate
import kotlinx.android.synthetic.main.beer_list_holder.view.*

class BeerAdapter constructor(
    private var beerList: MutableList<BeerModel>,
    private val listener: BeerClickListener,
    private val selectedIds: MutableList<Int>
) : RecyclerView.Adapter<BeerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val view: View = parent.inflate(R.layout.beer_list_holder, false)
        return BeerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return beerList.size
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.bindItem(beerList[position], listener, selectedIds.contains(beerList[position].id))
        holder.itemView.ivFavorite.setOnClickListener {
            val beer = beerList[holder.adapterPosition]
            if(selectedIds.contains(beer.id)){
                selectedIds.remove(beer.id)
            }else{
                selectedIds.add(beer.id)
            }
            notifyItemChanged(holder.adapterPosition)
            listener.beerFavoriteChanged(selectedIds)
        }
    }

    fun updateList(newBeerList: List<BeerModel>) {
        beerList.clear()
        beerList.addAll(newBeerList)
        notifyDataSetChanged()
    }
}