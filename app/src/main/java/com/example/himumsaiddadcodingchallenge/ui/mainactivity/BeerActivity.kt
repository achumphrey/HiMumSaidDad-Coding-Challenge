package com.example.himumsaiddadcodingchallenge.ui.mainactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.himumsaiddadcodingchallenge.R
import com.example.himumsaiddadcodingchallenge.data.BeerModel
import com.example.himumsaiddadcodingchallenge.di.DaggerBeerComponent
import com.example.himumsaiddadcodingchallenge.di.RepositoryModule
import com.example.himumsaiddadcodingchallenge.di.WebServicesModule
import com.example.himumsaiddadcodingchallenge.ui.adapter.BeerAdapter
import com.example.himumsaiddadcodingchallenge.ui.listener.BeerClickListener
import com.example.himumsaiddadcodingchallenge.viewmodel.BeerViewModel
import com.example.himumsaiddadcodingchallenge.viewmodel.BeerViewModelFactory
import kotlinx.android.synthetic.main.beer_activity.*
import javax.inject.Inject

class BeerActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: BeerViewModelFactory
    private lateinit var viewModel: BeerViewModel
    private lateinit var beerAdapter: BeerAdapter
    companion object{const val INTENT_MESSAGE = "message"}
    private val beerClickListener: BeerClickListener = object : BeerClickListener {
        override fun beerClickListener(beerList: BeerModel) {
            intent = Intent(this@BeerActivity, BeerDetailsActivity::class.java)
            intent.putExtra (INTENT_MESSAGE, beerList)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.beer_activity)

        getDependency()
        setupRecyclerView()

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(BeerViewModel::class.java)

        viewModel.beerList.observe(this, Observer {
            beerAdapter.updateList(it)

        })

        viewModel.errorMessage.observe(this, Observer {
            tvErrorMessage.text = it
        })

        viewModel.loadingState.observe(this, Observer {
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
        beerAdapter = BeerAdapter(mutableListOf(), beerClickListener)
        rvBeerList.adapter = beerAdapter
    }

}