package com.example.himumsaiddadcodingchallenge.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Malt(
    @SerializedName("amount")
    val amount: AmountX,
    @SerializedName("name")
    val name: String
):Parcelable