package pl.org.seva.victorweather.datasource

import pl.org.seva.victorweather.data.datasource.WeatherDataSource
import pl.org.seva.victorweather.data.model.WeatherDataModel
import pl.org.seva.victorweather.datasource.api.WeatherService
import pl.org.seva.victorweather.datasource.mapper.WeatherDataSourceToDataMapper

class WeatherLiveDataSource(
    private val weatherDataSourceToDataMapper: WeatherDataSourceToDataMapper,
    private val weatherService: WeatherService,
) : WeatherDataSource {

    override suspend fun fetchWeather(lat: Double, lon: Double): WeatherDataModel {
        return weatherDataSourceToDataMapper.toData(weatherService.fetchWeather(lat, lon))
    }

}
