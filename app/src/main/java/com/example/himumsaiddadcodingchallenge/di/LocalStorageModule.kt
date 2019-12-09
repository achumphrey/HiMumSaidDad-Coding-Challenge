package com.example.himumsaiddadcodingchallenge.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
class LocalStorageModule(private val application: Application) {

    @Provides
    fun provideContext(): Context {
        return application
    }

    @Provides
    fun provideSharedPreferences(): SharedPreferences {
        return application.getSharedPreferences("MY_PREF", Context.MODE_PRIVATE)
    }

}