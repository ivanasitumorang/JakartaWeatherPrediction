package com.azuka.jakartaweatherprediction

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.azuka.jakartaweatherprediction.entities.Jakarta
import com.azuka.jakartaweatherprediction.network.WeatherApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@Suppress("DEPRECATION")
@RunWith(AndroidJUnit4::class)

/*Test description :
    To check if the current weather sucessfully fetched with HTTP Code 200 OK
*/
class CurrentWeatherTest {

    private val HTTP_RESPONSE_OK: Int = 200

    @Test
    fun getCurrentWeather(){
        runBlocking {
            val getForecastDeferred = WeatherApi.retrofitService
                .getCurrentWeatherByCoordinate(WeatherApi.API_KEY, Jakarta().coordinate.lat, Jakarta().coordinate.lon)

            val result = getForecastDeferred.await()
            Assert.assertEquals(HTTP_RESPONSE_OK, result.code)
        }
    }
}