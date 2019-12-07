package com.example.himumsaiddadcodingchallenge.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AmountX(
    @SerializedName("unit")
    val unit: String,
    @SerializedName("value")
    val value: Double
):Parcelable