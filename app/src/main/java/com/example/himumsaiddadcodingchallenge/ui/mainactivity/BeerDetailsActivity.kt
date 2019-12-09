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

        val maltBuilder = StringBuilder()
        for (i in beerList.ingredients.malt.indices) {
            maltBuilder.append("Name : ${ beerList.ingredients.malt[i].name} " +
                    "\nAmount : ${beerList.ingredients.malt[i].amount.value}" +
                    "${beerList.ingredients.malt[i].amount.unit}\n\n")
        }
        tvMalt.text = "Malt :\n$maltBuilder"

        val hopsBuilder = StringBuilder()
        for (i in beerList.ingredients.hops.indices) {
            hopsBuilder.append("Name : ${beerList.ingredients.hops[i].name}" +
                    "\nAmount : ${beerList.ingredients.hops[i].amount.value} " +
                    "${beerList.ingredients.hops[i].amount.unit}  \n" +
                    "Add : ${beerList.ingredients.hops[i].add} \n" +
                    "Attribute : ${beerList.ingredients.hops[i].attribute} \n\n")
        }
        tvHops.text = "Hops :\n$hopsBuilder"

        val foodPairingBuilder = StringBuilder()
        for (i in beerList.foodPairing.indices) {
            foodPairingBuilder.append("${beerList.foodPairing[i]} \n")
        }
        tvFoodPairing.text = "Food Pairing:\n$foodPairingBuilder"

    }

}
