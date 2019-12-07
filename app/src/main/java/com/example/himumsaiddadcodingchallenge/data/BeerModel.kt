package com.example.himumsaiddadcodingchallenge.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class BeerModel(
    @SerializedName("abv")
    val abv: Double,
    @SerializedName("description")
    val description: String,
    @SerializedName("food_pairing")
    val foodPairing: List<String>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("ingredients")
    val ingredients: Ingredients,
    @SerializedName("name")
    val name: String

):Parcelable