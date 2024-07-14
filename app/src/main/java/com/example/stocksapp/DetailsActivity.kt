package com.example.stocksapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.gson.JsonObject

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory





class DetailsActivity : AppCompatActivity() {

    private lateinit var symbolTextView: TextView
    private lateinit var nameTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var industryTextView: TextView
    private lateinit var sectorTextView: TextView
    private lateinit var highTextView: TextView
    private lateinit var lowTextView: TextView
    private lateinit var marketCapTextView: TextView
    private lateinit var peRatioTextView: TextView
    private lateinit var betaTextView: TextView
    private lateinit var dividendYieldTextView: TextView
    private lateinit var profitMarginTextView: TextView
    private lateinit var errorView: View
    private lateinit var emptyView: View
    private lateinit var ticker: String
    private lateinit var lineChart: LineChart





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        lineChart = findViewById(R.id.lineChart)

//        val tickerTextView = findViewById<TextView>(R.id.text)

        // Retrieve ticker from intent extras
         ticker = intent.getStringExtra("ticker")  ?: ""

        // Display ticker in TextView or wherever needed
//        tickerTextView.text = ticker

        symbolTextView = findViewById(R.id.symbol)
        nameTextView = findViewById(R.id.name)
        descriptionTextView = findViewById(R.id.description)
        industryTextView = findViewById(R.id.industry)
        sectorTextView = findViewById(R.id.sector)
        highTextView = findViewById(R.id.high)
        lowTextView = findViewById(R.id.low)
        marketCapTextView = findViewById(R.id.market_cap)
        peRatioTextView = findViewById(R.id.pe_ratio)
        betaTextView = findViewById(R.id.beta)
        dividendYieldTextView = findViewById(R.id.dividend_yield)
        profitMarginTextView = findViewById(R.id.profit_margin)


        errorView = findViewById(R.id.error_text)
        emptyView = findViewById(R.id.empty_text)

        fetchStockDetails()
    }

    private fun fetchStockDetails() {


        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.alphavantage.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(StockApiService::class.java)
        val call = service.getDailyPrices(ticker)


        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    response.body()?.let { jsonObject ->
                        parseResponse(jsonObject)
                    }
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                showError()
            }
        })

        service.getStockOverview(ticker).enqueue(object : Callback<StockOverviewResponse> {
            override fun onResponse(
                call: Call<StockOverviewResponse>,
                response: Response<StockOverviewResponse>
            ) {
                if (response.isSuccessful) {
                    val stockDetails = response.body()
                    if (stockDetails != null) {
                        showContent()
                        populateUI(stockDetails)
                    } else {
                        showEmptyState()
                    }
                } else {
                    showError()
                }
            }

            override fun onFailure(call: Call<StockOverviewResponse>, t: Throwable) {
                showError()
            }
        })
    }

    private fun parseResponse(jsonObject: JsonObject) {

        val timeSeries = jsonObject.getAsJsonObject("Time Series (Daily)")
        val entries = mutableListOf<Entry>()
        val dates = mutableListOf<String>()

        timeSeries.entrySet().forEachIndexed { index, entry ->
            val date = entry.key
            val close = entry.value.asJsonObject.get("4. close").asFloat
            entries.add(Entry(index.toFloat(), close))
            dates.add(date)
        }

        val dataSet = LineDataSet(entries, "Daily Close Prices")
        dataSet.color = resources.getColor(R.color.purple)
        dataSet.valueTextColor = resources.getColor(R.color.black)
        dataSet.lineWidth = 2f

        val lineData = LineData(dataSet)
        lineChart.data = lineData

        val xAxis: XAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.valueFormatter = IndexAxisValueFormatter(dates)

        val leftAxis: YAxis = lineChart.axisLeft
        leftAxis.setDrawGridLines(false)

        val rightAxis: YAxis = lineChart.axisRight
        rightAxis.setDrawGridLines(false)
        rightAxis.isEnabled = false

        lineChart.description.isEnabled = false
        lineChart.invalidate()
    }



    private fun showError() {

        errorView.visibility = View.VISIBLE
        emptyView.visibility = View.GONE
        symbolTextView.visibility = View.GONE
        nameTextView.visibility = View.GONE
        descriptionTextView.visibility = View.GONE
        industryTextView.visibility = View.GONE
        sectorTextView.visibility = View.GONE
        highTextView.visibility = View.GONE
        lowTextView.visibility = View.GONE
        marketCapTextView.visibility = View.GONE
        peRatioTextView.visibility = View.GONE
        betaTextView.visibility = View.GONE
        dividendYieldTextView.visibility = View.GONE
        profitMarginTextView.visibility = View.GONE
    }

    private fun showEmptyState() {

        errorView.visibility = View.GONE
        emptyView.visibility = View.VISIBLE
        symbolTextView.visibility = View.GONE
        nameTextView.visibility = View.GONE
        descriptionTextView.visibility = View.GONE
        industryTextView.visibility = View.GONE
        sectorTextView.visibility = View.GONE
        highTextView.visibility = View.GONE
        lowTextView.visibility = View.GONE
        marketCapTextView.visibility = View.GONE
        peRatioTextView.visibility = View.GONE
        betaTextView.visibility = View.GONE
        dividendYieldTextView.visibility = View.GONE
        profitMarginTextView.visibility = View.GONE
    }

    private fun showContent() {

        errorView.visibility = View.GONE
        emptyView.visibility = View.GONE
        symbolTextView.visibility = View.VISIBLE
        nameTextView.visibility = View.VISIBLE
        descriptionTextView.visibility = View.VISIBLE
        industryTextView.visibility = View.VISIBLE
        sectorTextView.visibility = View.VISIBLE
        highTextView.visibility = View.VISIBLE
        lowTextView.visibility = View.VISIBLE
        marketCapTextView.visibility = View.VISIBLE
        peRatioTextView.visibility = View.VISIBLE
        betaTextView.visibility = View.VISIBLE
        dividendYieldTextView.visibility = View.VISIBLE
        profitMarginTextView.visibility = View.VISIBLE
    }

    private fun populateUI(stockDetails: StockOverviewResponse) {
        symbolTextView.text = stockDetails.Symbol ?: ""
        nameTextView.text = stockDetails.Name ?: ""
        descriptionTextView.text = stockDetails.Description ?: ""
        industryTextView.text = "Industry: ${stockDetails.Industry ?: ""}"
        sectorTextView.text = "Sector: ${stockDetails.Sector ?: ""}"
        highTextView.text = "52 Week High: ${stockDetails.FiftyTwoWeekHigh ?: ""}"
        lowTextView.text = "52 Week Low: ${stockDetails.FiftyTwoWeekLow ?: ""}"
        marketCapTextView.text = stockDetails.MarketCapitalization ?: ""
        peRatioTextView.text = stockDetails.PERatio ?: ""
        betaTextView.text = stockDetails.Beta ?: ""
        dividendYieldTextView.text = stockDetails.DividendYield ?: ""
        profitMarginTextView.text = stockDetails.ProfitMargin ?: ""
    }
}

