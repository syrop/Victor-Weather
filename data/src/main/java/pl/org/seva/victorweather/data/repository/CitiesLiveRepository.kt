package pl.org.seva.victorweather.data.repository

import pl.org.seva.victorweather.data.datasource.GeocodingDataSource
import pl.org.seva.victorweather.data.mapper.GeocodingDataToDomainMapper
import pl.org.seva.victorweather.data.model.GeocodingDataModel
import pl.org.seva.victorweather.domain.model.GeocodingDomainModel
import pl.org.seva.victorweather.domain.repository.CitiesRepository

class CitiesLiveRepository(
    private val geocodingDataToDomainMapper: GeocodingDataToDomainMapper,
    private val geocodingDataSource: GeocodingDataSource,
) : CitiesRepository {

    override suspend fun findCities(city: String): GeocodingDomainModel {
        return geocodingDataToDomainMapper.toData(
            GeocodingDataModel(geocodingDataSource.getGeocoding(city).cities)
        )
    }

}
