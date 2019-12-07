package com.example.himumsaiddadcodingchallenge.di

import com.example.himumsaiddadcodingchallenge.data.remote.WebServices
import com.example.himumsaiddadcodingchallenge.data.repository.BeerRepoImp
import com.example.himumsaiddadcodingchallenge.data.repository.Repository
import com.example.himumsaiddadcodingchallenge.viewmodel.BeerViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideViewModelFactory (repository: Repository):BeerViewModelFactory{
        return  BeerViewModelFactory(repository)
    }

    @Singleton
    @Provides
    fun provideBeerRepository(webServices: WebServices): Repository{
        return BeerRepoImp(webServices )
    }
}