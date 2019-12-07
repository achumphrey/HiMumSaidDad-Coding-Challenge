package com.example.himumsaiddadcodingchallenge.data.repository

import com.example.himumsaiddadcodingchallenge.data.BeerModel
import com.example.himumsaiddadcodingchallenge.data.remote.WebServices
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BeerRepoImp @Inject constructor(private val webServices: WebServices):Repository {
    override fun getListOfBeers(): Single<List<BeerModel>> {
       return webServices.getBeerList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}