package com.example.himumsaiddadcodingchallenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.himumsaiddadcodingchallenge.data.*
import com.example.himumsaiddadcodingchallenge.data.repository.Repository
import com.nhaarman.mockitokotlin2.atLeast
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.net.UnknownHostException

@RunWith(MockitoJUnitRunner::class)
class BeerViewModelTest {

    @Rule
    @JvmField
    val rule:TestRule = InstantTaskExecutorRule()

    private val beerListLDObserver: Observer<List<BeerModel>> = mock()
    private val errorMessageLDObserver: Observer<String> = mock()
    private val loadingStateLDObserver: Observer<BeerViewModel.LoadingState> = mock()
    private lateinit var beerModel: BeerModel
    private var hopList = mutableListOf<Hop>()
    private var maltList = mutableListOf<Malt>()
    private val foodPairing: List<String> = listOf("any", "any")
    private lateinit var ingredients: Ingredients
    private lateinit var amount: Amount
    private lateinit var viewModel: BeerViewModel
    private val beerList: MutableList<BeerModel> = mutableListOf()

    @Mock
    lateinit var repository: Repository

    @Before
    fun setUp() {
        viewModel = BeerViewModel(repository)

        amount = Amount("any", 2.0)
        hopList.add(Hop( "any", amount,"any", "any"))
        maltList.add(Malt( amount, "any"))
        ingredients = Ingredients( hopList, maltList, "yeast")
        beerModel = BeerModel(2.0, "any", foodPairing, 10, "any", ingredients, "any")
        beerList.add(beerModel)

        viewModel.beerList.observeForever(beerListLDObserver)
        viewModel.errorMessage.observeForever(errorMessageLDObserver)
        viewModel.loadingState.observeForever(loadingStateLDObserver)
    }

    @Test
    fun fetchBeerList_ReturnBeer_WithSuccess() {

        `when`(repository.getListOfBeers()).thenReturn(Single.just(beerList))

        viewModel.fetchBeerList()

        verify(repository, atLeast(1)).getListOfBeers()
        verify(beerListLDObserver, atLeast(1)).onChanged(beerList)
        verify(errorMessageLDObserver, atLeast(0)).onChanged("Any")
        verify(
            loadingStateLDObserver,
            atLeast(1)
        ).onChanged(BeerViewModel.LoadingState.SUCCESS)
    }

    @Test
    fun fetchBeerList_NoReturnBeer_EmptyList() {

        val beerListEmpty: MutableList<BeerModel> = mutableListOf()

        `when`(repository.getListOfBeers()).thenReturn(Single.just(beerListEmpty))

        viewModel.fetchBeerList()

        verify(repository, atLeast(1)).getListOfBeers()
        verify(beerListLDObserver, atLeast(0)).onChanged(emptyList())
        verify(errorMessageLDObserver, atLeast(1)).onChanged("No Data Found")
        verify(loadingStateLDObserver, atLeast(1)).onChanged(BeerViewModel.LoadingState.ERROR)
    }

    @Test
    fun fetchBeerList_NoReturnBeer_NoNetwork() {

        `when`(repository.getListOfBeers()).thenReturn(
            Single.error(
                UnknownHostException("No Network")
            )
        )
        viewModel.fetchBeerList()

        verify(repository, atLeast(1)).getListOfBeers()
        verify(beerListLDObserver, atLeast(0)).onChanged(null)
        verify(errorMessageLDObserver, atLeast(1)).onChanged("No Network")
        verify(loadingStateLDObserver, atLeast(1)).onChanged(BeerViewModel.LoadingState.ERROR)
    }

    @Test
    fun fetchBeerList_NoReturnBeer_WithError() {

        `when`(repository.getListOfBeers()).thenReturn(Single.error(RuntimeException("Something Wrong")))

        viewModel.fetchBeerList()

        verify(repository, atLeast(1)).getListOfBeers()
        verify(beerListLDObserver, atLeast(0)).onChanged(null)
        verify(errorMessageLDObserver, atLeast(1)).onChanged("Something Wrong")
        verify(loadingStateLDObserver, atLeast(1)).onChanged(BeerViewModel.LoadingState.ERROR)
    }
}