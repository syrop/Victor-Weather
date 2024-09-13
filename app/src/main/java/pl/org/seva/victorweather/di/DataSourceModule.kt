package pl.org.seva.victorweather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.org.seva.victorweather.data.datasource.GeocodingDataSource
import pl.org.seva.victorweather.data.datasource.WeatherDataSource
import pl.org.seva.victorweather.datasource.GeocodingLiveDataSource
import pl.org.seva.victorweather.datasource.WeatherLiveDataSource
import pl.org.seva.victorweather.datasource.api.GeocodingService
import pl.org.seva.victorweather.datasource.api.GeocodingServiceFactory
import pl.org.seva.victorweather.datasource.api.WeatherService
import pl.org.seva.victorweather.datasource.api.WeatherServiceFactory
import pl.org.seva.victorweather.datasource.mapper.CityDataSourceToDataMapper
import pl.org.seva.victorweather.datasource.mapper.WeatherDataSourceToDataMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun providesCityDataSourceToDataMapper() = CityDataSourceToDataMapper()

    @Provides
    fun provideWeatherDataSourceToDataMapper() = WeatherDataSourceToDataMapper()

    @Provides
    fun provideGeocodingService() = GeocodingServiceFactory().getGeocodingService()

    @Provides
    fun provideWeatherService() = WeatherServiceFactory().getWeatherService()

    @Provides
    @Singleton
    fun provideGeocodingDataSource(
        cityDataSourceToDataMapper: CityDataSourceToDataMapper,
        geocodingService: GeocodingService,
    ): GeocodingDataSource = GeocodingLiveDataSource(
        cityDataSourceToDataMapper,
        geocodingService,
    )

    @Provides
    @Singleton
    fun provideWeatherDataSource(
        weatherDataSourceToDataMapper: WeatherDataSourceToDataMapper,
        weatherService: WeatherService,
    ): WeatherDataSource = WeatherLiveDataSource(weatherDataSourceToDataMapper, weatherService)

}
