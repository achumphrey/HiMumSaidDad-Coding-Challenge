package com.example.himumsaiddadcodingchallenge.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hop(
    @SerializedName("add")
    val add: String,
    @SerializedName("amount")
    val amount: Amount,
    @SerializedName("attribute")
    val attribute: String,
    @SerializedName("name")
    val name: String
):Parcelable