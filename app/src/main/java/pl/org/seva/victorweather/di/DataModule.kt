package pl.org.seva.victorweather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.org.seva.victorweather.data.datasource.GeocodingDataSource
import pl.org.seva.victorweather.data.mapper.GeocodingDataToDomainMapper
import pl.org.seva.victorweather.data.repository.CitiesLiveRepository
import pl.org.seva.victorweather.datasource.GeocodingLiveDataSource
import pl.org.seva.victorweather.datasource.api.GeocodingService
import pl.org.seva.victorweather.datasource.mapper.GeocodingDataSourceToDataMapper
import pl.org.seva.victorweather.domain.repository.CitiesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun providesGeocodingDataToDomainMapper() = GeocodingDataToDomainMapper()

    @Provides
    @Singleton
    fun providesCitiesRepository(
        geocodingDataToDomainMapper: GeocodingDataToDomainMapper,
        geocodingDataSource: GeocodingDataSource,
    ): CitiesRepository = CitiesLiveRepository(
        geocodingDataToDomainMapper,
        geocodingDataSource,
    )

}
