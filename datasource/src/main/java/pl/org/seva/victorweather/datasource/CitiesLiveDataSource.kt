package pl.org.seva.victorweather.datasource

import android.content.Context
import androidx.room.Room.databaseBuilder
import pl.org.seva.victorweather.data.datasource.CitiesDataSource
import pl.org.seva.victorweather.data.model.CityDataModel
import pl.org.seva.victorweather.datasource.api.GeocodingService
import pl.org.seva.victorweather.datasource.database.WeatherDatabase
import pl.org.seva.victorweather.datasource.mapper.CityDataSourceToDataMapper
import pl.org.seva.victorweather.datasource.mapper.CityDataToDataSourceMapper

class CitiesLiveDataSource(
    ctx: Context,
    private val cityDataSourceToDataMapper: CityDataSourceToDataMapper,
    private val cityDataToDataSourceMapper: CityDataToDataSourceMapper,
    private val geocodingService: GeocodingService,
) : CitiesDataSource {

    private val weatherDb = databaseBuilder(
        ctx,
        WeatherDatabase::class.java,
        DATABASE_NAME,
    ).build()

    override suspend fun fetchCities(city: String): List<CityDataModel> {
        return geocodingService.fetchCities(city).map {
            cityDataSourceToDataMapper.toData(it)
        }
    }

    override suspend fun save(city: CityDataModel) {
        weatherDb.cityDao().insert(cityDataToDataSourceMapper.toDataSource(city))
    }

    override suspend fun load(): List<CityDataModel> {
        return weatherDb.cityDao().getAll().map { cityDataSourceToDataMapper.toData(it) }
    }

    companion object {
        const val DATABASE_NAME = "weather"
    }

}
