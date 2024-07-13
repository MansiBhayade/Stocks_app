package com.example.stocksapp

data class StockDataResponse(
    val top_gainers: List<Stock>,
    val top_losers: List<Stock>
)

data class Stock(
    val ticker: String,
    val price: String,
    val change_percentage: String
)

data class StockOverviewResponse(
    val Symbol: String?,
    val Name: String?,
    val Description: String?,
    val Industry: String?,
    val Sector: String?,
    val FiftyTwoWeekHigh: String?,
    val FiftyTwoWeekLow: String?,
    val MarketCapitalization: String?,
    val PERatio: String?,
    val Beta: String?,
    val DividendYield: String?,
    val ProfitMargin: String?
)

