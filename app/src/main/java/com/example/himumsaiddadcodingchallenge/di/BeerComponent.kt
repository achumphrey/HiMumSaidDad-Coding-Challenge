package com.example.himumsaiddadcodingchallenge.di

import com.example.himumsaiddadcodingchallenge.ui.mainactivity.BeerActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, WebServicesModule::class, LocalStorageModule::class])
interface BeerComponent  {
    fun inject(beerActivity: BeerActivity)
}