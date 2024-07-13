package com.example.stocksapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragmentTwo : Fragment() , StockAdapter.OnStockItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StockAdapter
    private val stocks = mutableListOf<Stock>()
    private lateinit var progressBar: View
    private lateinit var errorText: View
    private lateinit var emptyText: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_one, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        adapter = StockAdapter(stocks, false, this)
        recyclerView.adapter = adapter

        progressBar = view.findViewById(R.id.progress_bar)
        errorText = view.findViewById(R.id.error_text)
        emptyText = view.findViewById(R.id.empty_text)

        fetchToplosers()


        return view
    }

    override fun onStockItemClick(ticker: String) {
        // Handle item click here, e.g., navigate to a new screen with the ticker
        val intent = Intent(requireContext(), DetailsActivity::class.java)
        intent.putExtra("ticker", ticker)
        startActivity(intent)
    }

    private fun fetchToplosers() {
        showLoading()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.alphavantage.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(StockApiService::class.java)
        api.getTopLosers().enqueue(object : Callback<StockDataResponse> {
            override fun onResponse(call: Call<StockDataResponse>, response: Response<StockDataResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        stocks.clear()
                        stocks.addAll(it.top_losers)
                        adapter.notifyDataSetChanged()
                        if (stocks.isEmpty()) {
                            showEmptyState()
                        } else {
                            showContent()
                        }
                    } ?: showEmptyState()
                } else {
                    showError()
                }
            }

            override fun onFailure(call: Call<StockDataResponse>, t: Throwable) {
                showError()
            }
        })
    }




    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        errorText.visibility = View.GONE
        emptyText.visibility = View.GONE
    }

    private fun showError() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE
        errorText.visibility = View.VISIBLE
        emptyText.visibility = View.GONE
    }

    private fun showEmptyState() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE
        errorText.visibility = View.GONE
        emptyText.visibility = View.VISIBLE
    }

    private fun showContent() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        errorText.visibility = View.GONE
        emptyText.visibility = View.GONE
    }
}