package pl.org.seva.victorweather.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.org.seva.victorweather.data.datasource.CitiesDataSource
import pl.org.seva.victorweather.data.datasource.WeatherDataSource
import pl.org.seva.victorweather.datasource.CitiesLiveDataSource
import pl.org.seva.victorweather.datasource.WeatherLiveDataSource
import pl.org.seva.victorweather.datasource.api.GeocodingService
import pl.org.seva.victorweather.datasource.api.GeocodingServiceFactory
import pl.org.seva.victorweather.datasource.api.WeatherService
import pl.org.seva.victorweather.datasource.api.WeatherServiceFactory
import pl.org.seva.victorweather.datasource.mapper.CityDataSourceToDataMapper
import pl.org.seva.victorweather.datasource.mapper.CityDataToDataSourceMapper
import pl.org.seva.victorweather.datasource.mapper.WeatherDataSourceToDataMapper
import pl.org.seva.victorweather.datasource.mapper.WeatherDataToDataSourceMapper
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
    fun providesCityDataToDataSourceMapper() = CityDataToDataSourceMapper()

    @Provides
    fun providesWeatherDataToDataSourceMapper() = WeatherDataToDataSourceMapper()

    @Provides
    @Singleton
    fun provideGeocodingDataSource(
        @ApplicationContext context: Context,
        cityDataSourceToDataMapper: CityDataSourceToDataMapper,
        cityDataToDataSourceMapper: CityDataToDataSourceMapper,
        geocodingService: GeocodingService,
    ): CitiesDataSource = CitiesLiveDataSource(
        context,
        cityDataSourceToDataMapper,
        cityDataToDataSourceMapper,
        geocodingService,
    )

    @Provides
    @Singleton
    fun provideWeatherDataSource(
        @ApplicationContext context: Context,
        weatherDataSourceToDataMapper: WeatherDataSourceToDataMapper,
        weatherDataToDataSourceMapper: WeatherDataToDataSourceMapper,
        weatherService: WeatherService,
    ): WeatherDataSource = WeatherLiveDataSource(
        context,
        weatherDataSourceToDataMapper,
        weatherDataToDataSourceMapper,
        weatherService,
    )

}
