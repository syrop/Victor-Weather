package pl.org.seva.victorweather.data.repository

import pl.org.seva.victorweather.data.datasource.WeatherDataSource
import pl.org.seva.victorweather.data.mapper.WeatherDataToDomainMapper
import pl.org.seva.victorweather.domain.model.WeatherDomainModel
import pl.org.seva.victorweather.domain.repository.WeatherRepository

class WeatherLiveRepository(
    private val weatherDataToDomainMapper: WeatherDataToDomainMapper,
    private val weatherDataSource: WeatherDataSource,
) : WeatherRepository {

    override suspend fun fetchWeather(lat: Double, lon: Double): WeatherDomainModel {
        return weatherDataToDomainMapper.toDomain(weatherDataSource.fetchWeather(lat, lon))
    }

}
