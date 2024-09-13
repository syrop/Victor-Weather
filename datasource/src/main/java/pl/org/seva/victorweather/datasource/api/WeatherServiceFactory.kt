package pl.org.seva.victorweather.datasource.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherServiceFactory {

    fun getWeatherService(): WeatherService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherService::class.java)

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/3.0/"
    }

}
