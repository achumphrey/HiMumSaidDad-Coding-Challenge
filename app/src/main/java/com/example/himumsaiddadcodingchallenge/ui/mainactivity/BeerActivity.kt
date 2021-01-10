package com.example.himumsaiddadcodingchallenge.ui.mainactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.himumsaiddadcodingchallenge.R
import com.example.himumsaiddadcodingchallenge.data.BeerModel
import com.example.himumsaiddadcodingchallenge.data.PreferenceHelper
import com.example.himumsaiddadcodingchallenge.di.DaggerBeerComponent
import com.example.himumsaiddadcodingchallenge.di.LocalStorageModule
import com.example.himumsaiddadcodingchallenge.di.RepositoryModule
import com.example.himumsaiddadcodingchallenge.di.WebServicesModule
import com.example.himumsaiddadcodingchallenge.ui.adapter.BeerAdapter
import com.example.himumsaiddadcodingchallenge.ui.listener.BeerClickListener
import com.example.himumsaiddadcodingchallenge.viewmodel.BeerViewModel
import com.example.himumsaiddadcodingchallenge.viewmodel.BeerViewModelFactory
import com.google.gson.Gson
import kotlinx.android.synthetic.main.beer_activity.*
import javax.inject.Inject

class BeerActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: BeerViewModelFactory

    @Inject
    lateinit var preferenceHelper: PreferenceHelper

    private lateinit var viewModel: BeerViewModel
    private lateinit var beerAdapter: BeerAdapter

    companion object {
        const val INTENT_MESSAGE = "message"
    }

    private val beerClickListener: BeerClickListener = object : BeerClickListener {

        override fun beerClickListener(beer: BeerModel) {

            val gson = Gson()
            intent = Intent(this@BeerActivity, BeerDetailsActivity::class.java)
            intent.putExtra(INTENT_MESSAGE, gson.toJson(beer))
            startActivity(intent)
        }

        override fun beerFavoriteChanged(selectedIds: List<Int>) {
            preferenceHelper.storeFavoriteIds(selectedIds)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.beer_activity)

        getDependency()
        setupRecyclerView()

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(BeerViewModel::class.java)

        viewModel.getLDBeerList().observe(this, Observer {
            beerAdapter.updateList(it)

        })

        viewModel.getLDErrorMessage() .observe(this, Observer {
            tvErrorMessage.text = it
        })

        viewModel.getLDLoadingState().observe(this, Observer {
            when (it) {
                BeerViewModel.LoadingState.LOADING -> displayProgressbar()
                BeerViewModel.LoadingState.SUCCESS -> displayImageList()
                BeerViewModel.LoadingState.ERROR -> displayErrorMessage()
                else -> displayErrorMessage()
            }
        })

        viewModel.fetchBeerList()

        btnRetry.setOnClickListener {
            viewModel.fetchBeerList()
        }
    }

    private fun getDependency() {

        DaggerBeerComponent.builder()
            .repositoryModule(RepositoryModule())
            .webServicesModule(WebServicesModule())
            .localStorageModule(LocalStorageModule(application))
            .build()
            .inject(this)
    }

    private fun displayProgressbar() {

        prgBar.visibility = View.VISIBLE
        rvBeerList.visibility = View.GONE
        tvErrorMessage.visibility = View.GONE
        btnRetry.visibility = View.GONE
    }

    private fun displayErrorMessage() {

        tvErrorMessage.visibility = View.VISIBLE
        btnRetry.visibility = View.VISIBLE
        rvBeerList.visibility = View.GONE
        prgBar.visibility = View.GONE
    }

    private fun displayImageList() {

        tvErrorMessage.visibility = View.GONE
        btnRetry.visibility = View.GONE
        rvBeerList.visibility = View.VISIBLE
        prgBar.visibility = View.GONE
    }

    private fun setupRecyclerView() {

        rvBeerList.layoutManager = LinearLayoutManager(this)

        beerAdapter =
            BeerAdapter(
                mutableListOf(),
                beerClickListener,
                preferenceHelper.getFavorites())

        rvBeerList.adapter = beerAdapter
    }

}
