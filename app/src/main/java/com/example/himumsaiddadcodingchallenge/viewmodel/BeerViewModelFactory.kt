package com.example.himumsaiddadcodingchallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.himumsaiddadcodingchallenge.data.repository.Repository
import javax.inject.Inject

class BeerViewModelFactory constructor(private val repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BeerViewModel(repository) as T
    }


}
