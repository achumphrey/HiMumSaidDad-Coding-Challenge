package com.example.himumsaiddadcodingchallenge.ui.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.himumsaiddadcodingchallenge.R
import com.example.himumsaiddadcodingchallenge.data.BeerModel
import com.example.himumsaiddadcodingchallenge.ui.mainactivity.BeerActivity.Companion.INTENT_MESSAGE
import kotlinx.android.synthetic.main.beer_detail_activity.*

class BeerDetailsActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.beer_detail_activity)

        val beerList : BeerModel = intent.getParcelableExtra (INTENT_MESSAGE)
        tvDescription.text = "Description: \n${beerList.description} \n"

        tvIngredients.text = "Ingredients:\n"
        var malt:String = ""
        for (i in beerList.ingredients.malt.indices) {
           malt +=  "Name : ${ beerList.ingredients.malt[i].name} " +
                   "\nAmount : ${beerList.ingredients.malt[i].amount.value}" +
                   "${beerList.ingredients.malt[i].amount.unit}\n\n"
        }
        tvMalt.text = "Malt :\n$malt"


        var hops:String = ""
        for (i in beerList.ingredients.hops.indices) {
            hops +=  "Name : ${beerList.ingredients.hops[i].name}" +
                    "\nAmount : ${beerList.ingredients.hops[i].amount.value} " +
                    "${beerList.ingredients.hops[i].amount.unit}  \n" +
                    "Add : ${beerList.ingredients.hops[i].add} \n" +
                    "Attribute : ${beerList.ingredients.hops[i].attribute} \n\n"
        }
        tvHops.text = "Hops :\n$hops"

        var foodpairing:String = ""
        for (i in beerList.foodPairing.indices) {
            foodpairing +=  "${beerList.foodPairing[i]} \n"
        }
        tvFoodPairing.text = "Food Pairing:\n$foodpairing"

    }

}
