package com.example.arinspectexercise

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.example.arinspectexercise.model.Facts
import com.example.arinspectexercise.viewmodel.FactsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: FactsViewModel
    private var factsAdapter: FactsAdapter = FactsAdapter()
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)
        recyclerView.adapter = factsAdapter

        viewModel = ViewModelProviders.of(this).get(FactsViewModel::class.java)
        swipeRefreshLayout.setOnRefreshListener { viewModel.refresh() }

        fetchData()
    }

    private fun fetchData() {
        swipeRefreshLayout.isRefreshing = true
        viewModel.getFacts().observe(this,
            Observer<Facts> { factsModels ->
                swipeRefreshLayout.isRefreshing = false
                title = factsModels?.title
                factsAdapter.setData(factsModels?.rows)
            })
    }
}
