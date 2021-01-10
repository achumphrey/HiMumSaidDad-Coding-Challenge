package com.example.himumsaiddadcodingchallenge.data


import com.google.gson.annotations.SerializedName

data class Malt(
    @SerializedName("amount")
    val amount: Amount,
    @SerializedName("name")
    val name: String
)