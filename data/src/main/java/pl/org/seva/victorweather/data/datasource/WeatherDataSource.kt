package pl.org.seva.victorweather.data.datasource

import pl.org.seva.victorweather.data.model.CityDataModel
import pl.org.seva.victorweather.data.model.WeatherDataModel

interface WeatherDataSource {

    suspend fun fetchWeather(lat: Double, lon: Double): WeatherDataModel

    suspend fun fetchHistoricalWeather(city: String): WeatherDataModel

    suspend fun save(city: WeatherDataModel)

}
