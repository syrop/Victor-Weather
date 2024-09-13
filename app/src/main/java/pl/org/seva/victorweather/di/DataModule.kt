package pl.org.seva.victorweather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.org.seva.victorweather.data.datasource.GeocodingDataSource
import pl.org.seva.victorweather.data.mapper.CityDataToDomainMapper
import pl.org.seva.victorweather.data.repository.CitiesLiveRepository
import pl.org.seva.victorweather.domain.repository.CitiesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun providesCityDataToDomainMapper() = CityDataToDomainMapper()

    @Provides
    @Singleton
    fun providesCitiesRepository(
        geocodingDataToDomainMapper: CityDataToDomainMapper,
        geocodingDataSource: GeocodingDataSource,
    ): CitiesRepository = CitiesLiveRepository(
        geocodingDataToDomainMapper,
        geocodingDataSource,
    )

}
