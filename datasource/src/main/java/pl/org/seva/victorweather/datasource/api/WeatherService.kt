package pl.org.seva.victorweather.datasource.api

import pl.org.seva.victorweather.datasource.model.WeatherDataSourceModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("onecall")
    suspend fun fetchWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String = "minutely,hourly,daily,alerts",
        @Query("units") units: String = "metric",
        @Query("appid") id: String = APPID,
    ): WeatherDataSourceModel

    companion object {
        const val APPID = "83f8aa22f836aeee8b81c98f63bd1c06"
    }

}
