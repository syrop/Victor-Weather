package pl.org.seva.victorweather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.org.seva.victorweather.data.datasource.CitiesDataSource
import pl.org.seva.victorweather.data.datasource.WeatherDataSource
import pl.org.seva.victorweather.data.mapper.CityDataToDomainMapper
import pl.org.seva.victorweather.data.mapper.CityDomainToDataMapper
import pl.org.seva.victorweather.data.mapper.WeatherDataToDomainMapper
import pl.org.seva.victorweather.data.mapper.WeatherDomainToDataMapper
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
    fun providesCityDomainToDataMapper() = CityDomainToDataMapper()

    @Provides
    fun provideWeatherDomainToDataMapper() = WeatherDomainToDataMapper()

    @Provides
    @Singleton
    fun providesCitiesRepository(
        cityDataToDomainMapper: CityDataToDomainMapper,
        cityDomainToDataMapper: CityDomainToDataMapper,
        citiesDataSource: CitiesDataSource,
    ): CitiesRepository = CitiesLiveRepository(
        cityDataToDomainMapper,
        cityDomainToDataMapper,
        citiesDataSource,
    )

    @Provides
    @Singleton
    fun provideWeatherRepository(
        weatherDataToDomainMapper: WeatherDataToDomainMapper,
        weatherDomainToDataMapper: WeatherDomainToDataMapper,
        weatherDataSource: WeatherDataSource,
    ): WeatherRepository = WeatherLiveRepository(
        weatherDataToDomainMapper,
        weatherDomainToDataMapper,
        weatherDataSource,
    )

}
