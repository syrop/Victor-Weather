package pl.org.seva.victorweather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.org.seva.victorweather.data.datasource.GeocodingDataSource
import pl.org.seva.victorweather.data.datasource.WeatherDataSource
import pl.org.seva.victorweather.data.mapper.CityDataToDomainMapper
import pl.org.seva.victorweather.data.mapper.WeatherDataToDomainMapper
import pl.org.seva.victorweather.data.repository.CitiesLiveRepository
import pl.org.seva.victorweather.data.repository.WeatherLiveRepository
import pl.org.seva.victorweather.domain.repository.CitiesRepository
import pl.org.seva.victorweather.domain.repository.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun providesCityDataToDomainMapper() = CityDataToDomainMapper()

    @Provides
    fun providesWeatherDataToDomainMapper() = WeatherDataToDomainMapper()

    @Provides
    @Singleton
    fun providesCitiesRepository(
        cityDataToDomainMapper: CityDataToDomainMapper,
        cityDataSource: GeocodingDataSource,
    ): CitiesRepository = CitiesLiveRepository(
        cityDataToDomainMapper,
        cityDataSource,
    )

    @Provides
    @Singleton
    fun provideWeatherRepository(
        weatherDataToDomainMapper: WeatherDataToDomainMapper,
        weatherDataSource: WeatherDataSource,
    ): WeatherRepository = WeatherLiveRepository(
        weatherDataToDomainMapper,
        weatherDataSource,
    )

}
