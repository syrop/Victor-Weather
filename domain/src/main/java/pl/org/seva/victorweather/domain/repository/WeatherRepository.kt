package pl.org.seva.victorweather.domain.repository

import pl.org.seva.victorweather.domain.model.WeatherDomainModel

interface WeatherRepository {

    suspend fun fetchWeather(lat: Double, lon: Double): WeatherDomainModel

    suspend fun fetchWeatherHistory(city: String): WeatherDomainModel

    suspend fun save(weather: WeatherDomainModel)

}
