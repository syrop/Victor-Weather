package pl.org.seva.victorweather.datasource

import android.content.Context
import androidx.room.Room.databaseBuilder
import pl.org.seva.victorweather.data.datasource.WeatherDataSource
import pl.org.seva.victorweather.data.model.WeatherDataModel
import pl.org.seva.victorweather.datasource.api.WeatherService
import pl.org.seva.victorweather.datasource.database.WeatherDatabase
import pl.org.seva.victorweather.datasource.database.WeatherDatabase.Companion.DATABASE_NAME
import pl.org.seva.victorweather.datasource.mapper.WeatherDataSourceToDataMapper
import pl.org.seva.victorweather.datasource.mapper.WeatherDataToDataSourceMapper

class WeatherLiveDataSource(
    ctx: Context,
    private val weatherDataSourceToDataMapper: WeatherDataSourceToDataMapper,
    private val weatherDataToDataSourceMapper: WeatherDataToDataSourceMapper,
    private val weatherService: WeatherService,
) : WeatherDataSource {

    private val weatherDb = databaseBuilder(
        ctx,
        WeatherDatabase::class.java,
        DATABASE_NAME,
    ).build()

    override suspend fun fetchWeather(lat: Double, lon: Double): WeatherDataModel {
        return weatherDataSourceToDataMapper.toData(weatherService.fetchWeather(lat, lon))
    }

    override suspend fun fetchHistoricalWeather(city: String): WeatherDataModel {
        return weatherDataSourceToDataMapper.toData(weatherDb.weatherDao().get(city))
    }

    override suspend fun save(weather: WeatherDataModel) {
        weatherDb.weatherDao().insert(weatherDataToDataSourceMapper.toDataSource(weather))
    }

}
