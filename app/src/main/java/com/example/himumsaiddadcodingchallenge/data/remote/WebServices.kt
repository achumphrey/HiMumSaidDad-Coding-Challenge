package com.example.himumsaiddadcodingchallenge.data.remote

import com.example.himumsaiddadcodingchallenge.data.BeerModel
import com.example.himumsaiddadcodingchallenge.util.Constants
import io.reactivex.Single
import retrofit2.http.GET

interface WebServices {
    @GET(Constants.ENDPOINT_URL)
    fun getBeerList(): Single<List<BeerModel>>
}