package com.example.stocksapp

data class StockDataResponse(
    val top_gainers: List<Stock>
)

data class Stock(
    val ticker: String,
    val price: String,
    val change_percentage: String
)
