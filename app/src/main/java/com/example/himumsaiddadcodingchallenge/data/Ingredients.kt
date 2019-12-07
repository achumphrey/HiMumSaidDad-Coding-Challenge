package com.example.himumsaiddadcodingchallenge.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ingredients(
    @SerializedName("hops")
    val hops: List<Hop>,
    @SerializedName("malt")
    val malt: List<Malt>,
    @SerializedName("yeast")
    val yeast: String
):Parcelable