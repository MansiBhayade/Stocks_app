# Stocks App

## Installation

### Device must be connected to Internet. 
### The Api key has a per day limit, after exceeding limit data related to stocks will not be seen.
### You can log in to Alphavantage and generate your own apikey and can replace apikey in StockApiService.kt and use it.

1. Clone the repository:
    ```bash
    git clone git@github.com:MansiBhayade/Stocks_app.git
    ```

2. Open the project in Android Studio.

3. Build the project to download all the dependencies.

4. Run the project on an emulator or a physical device.

## Technologies Used

- Kotlin
- Retrofit
- MPAndroidChart
- RecyclerView

Endpoints used
- AlphaIntelligence- Top Gainers and Losers, to display top gainers and losers on explore screen
- Fundamentaldata-CompanyOverview, for overview screen
- Core Stock APIs- Daily, to draw line chart of prices 