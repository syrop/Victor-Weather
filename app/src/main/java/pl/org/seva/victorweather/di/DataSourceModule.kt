package pl.org.seva.victorweather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.org.seva.victorweather.data.datasource.GeocodingDataSource
import pl.org.seva.victorweather.datasource.GeocodingLiveDataSource
import pl.org.seva.victorweather.datasource.api.GeocodingService
import pl.org.seva.victorweather.datasource.api.GeocodingServiceFactory
import pl.org.seva.victorweather.datasource.mapper.CityDataSourceToDataMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun providesCityDataSourceToDataMapper() = CityDataSourceToDataMapper()

    @Provides
    fun provideGeocodingService() = GeocodingServiceFactory().getGeocodingService()

    @Provides
    @Singleton
    fun provideGeocodingDataSource(
        geocodingDataSourceToDataMapper: CityDataSourceToDataMapper,
        geocodingService: GeocodingService,
    ): GeocodingDataSource = GeocodingLiveDataSource(
        geocodingDataSourceToDataMapper,
        geocodingService,
    )

}
