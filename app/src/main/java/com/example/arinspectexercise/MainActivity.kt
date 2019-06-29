package com.example.arinspectexercise

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.arinspectexercise.model.network.Status
import com.example.arinspectexercise.viewmodel.FactsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: FactsViewModel
    private var factsAdapter: FactsAdapter = FactsAdapter()
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var errorMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)
        errorMessage = findViewById(R.id.error_message)

        recyclerView.adapter = factsAdapter

        viewModel = ViewModelProviders.of(this).get(FactsViewModel::class.java)
        swipeRefreshLayout.setOnRefreshListener { viewModel.refresh(true) }

        fetchData()
    }

    private fun fetchData() {
        swipeRefreshLayout.isRefreshing = true
        viewModel.getFacts().observe(this, Observer {
            it?.let {
                //Set loading state
                swipeRefreshLayout.isRefreshing = it.status == Status.LOADING

                //Set error message on failure and hide the view if message is null or empty
                if (it.data?.rows.isNullOrEmpty()) {
                    errorMessage.text = it.message
                    errorMessage.visibility = if (it.message.isNullOrEmpty()) View.GONE else View.VISIBLE
                }

                //Set ActionBar title
                title = it.data?.title

                //Set recyclerview adapter
                factsAdapter.setData(it.data?.rows)
            }
        })
    }
}
