package com.example.himumsaiddadcodingchallenge.data.repository

import com.example.himumsaiddadcodingchallenge.data.BeerModel
import io.reactivex.Single

interface Repository {
    fun getListOfBeers(): Single<List<BeerModel>>
}