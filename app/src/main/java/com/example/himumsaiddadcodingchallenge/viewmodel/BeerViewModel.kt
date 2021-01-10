package com.example.himumsaiddadcodingchallenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.himumsaiddadcodingchallenge.data.BeerModel
import com.example.himumsaiddadcodingchallenge.data.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException

class BeerViewModel constructor(private val repository: Repository): ViewModel() {

    private val  disposable: CompositeDisposable = CompositeDisposable()
    private val beerList: MutableLiveData<List<BeerModel>> = MutableLiveData()
    private val errorMessage: MutableLiveData<String> = MutableLiveData()
    private val loadingState = MutableLiveData<LoadingState>()

    fun fetchBeerList(){
        loadingState.value = LoadingState.LOADING
        disposable.add(
            repository.getListOfBeers()
                .subscribe({
                    if (it.isEmpty()){
                        errorMessage.value = "No Data Found"
                        loadingState.value = LoadingState.ERROR
                    }else{
                        beerList.value = it
                        loadingState.value = LoadingState.SUCCESS
                    }
                },{
                    it.printStackTrace()
                    when (it) {
                        is UnknownHostException -> errorMessage.value = "No Network"
                        else -> errorMessage.value = it.localizedMessage
                    }
                    loadingState.value = LoadingState.ERROR
                })

        )
    }

    enum class LoadingState {
        LOADING,
        SUCCESS,
        ERROR
    }

    fun getLDBeerList(): MutableLiveData<List<BeerModel>>{

        return beerList
    }

    fun getLDErrorMessage(): MutableLiveData<String>{

        return errorMessage
    }

    fun getLDLoadingState(): MutableLiveData<LoadingState>{

        return loadingState
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}
