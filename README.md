# Overview

This app is built using Kotlin + MVVM + Coroutines. The weather is fetched from [Open Weather Map API](https://openweathermap.org). I am using Retrofit 2 and OkHTTP 3 to fetch the data. 

![App screenshot](https://github.com/ivanasitumorang/JakartaWeatherPrediction/blob/master/screenshot/screenshot.jpeg)

## Detail Points

This is the points I used in building this app :
1. Kotlin (the language)
2. MVVM (the architecture)
3. Kotlin Coroutines (the asynchronous method for fetching the API data)
4. Retrofit + OkHTTP (the library to fetch the API)
5. Data Binding (to make binding to view easier + create extensions to bind data to view)
6. JUnit (to make the unit test)


## Current Weather

Current weather is fetched from this endpoint :

```kotlin
api.openweathermap.org/data/2.5/weather?appid=[API_KEY]&lat=[Jakarta latitude]&lon=[Jakarta longitude]
```

## Forecast Weather

Forecast weather (one day per 3 hour only)  is fetched from this endpoint :

```kotlin
api.openweathermap.org/data/2.5/forecast?appid=[API_KEY]&lat=[Jakarta latitude]&lon=[Jakarta longitude]
```

## Weather Testing
I create 2 test files. Those are Current Weather test and Forecast Weather test. You can check it [here](https://github.com/ivanasitumorang/JakartaWeatherPrediction/tree/master/app/src/androidTest/java/com/azuka/jakartaweatherprediction).
