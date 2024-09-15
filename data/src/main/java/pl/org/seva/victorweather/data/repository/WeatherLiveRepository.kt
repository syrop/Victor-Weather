package pl.org.seva.victorweather.data.repository

import pl.org.seva.victorweather.data.datasource.WeatherDataSource
import pl.org.seva.victorweather.data.mapper.CityDomainToDataMapper
import pl.org.seva.victorweather.data.mapper.WeatherDataToDomainMapper
import pl.org.seva.victorweather.data.mapper.WeatherDomainToDataMapper
import pl.org.seva.victorweather.domain.model.WeatherDomainModel
import pl.org.seva.victorweather.domain.repository.WeatherRepository

class WeatherLiveRepository(
    private val weatherDataToDomainMapper: WeatherDataToDomainMapper,
    private val weatherDomainToDataMapper: WeatherDomainToDataMapper,
    private val weatherDataSource: WeatherDataSource,
) : WeatherRepository {

    override suspend fun fetchWeather(lat: Double, lon: Double): WeatherDomainModel {
        return weatherDataToDomainMapper.toDomain(weatherDataSource.fetchWeather(lat, lon))
    }

    override suspend fun fetchWeatherHistory(city: String): WeatherDomainModel {
        return weatherDataToDomainMapper.toDomain(
            weatherDataSource.fetchHistoricalWeather(city)
        )
    }

    override suspend fun save(weather: WeatherDomainModel) {
        weatherDataSource.save(weatherDomainToDataMapper.toData(weather))
    }

}
