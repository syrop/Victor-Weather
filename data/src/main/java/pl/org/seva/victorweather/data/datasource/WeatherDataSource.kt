package pl.org.seva.victorweather.data.datasource

import pl.org.seva.victorweather.data.model.WeatherDataModel

interface WeatherDataSource {

    suspend fun fetchWeather(lat: Double, lon: Double): WeatherDataModel

}
