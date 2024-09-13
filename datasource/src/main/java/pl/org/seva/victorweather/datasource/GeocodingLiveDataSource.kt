package pl.org.seva.victorweather.datasource

import pl.org.seva.victorweather.data.datasource.GeocodingDataSource
import pl.org.seva.victorweather.data.model.CityDataModel
import pl.org.seva.victorweather.datasource.api.GeocodingService
import pl.org.seva.victorweather.datasource.mapper.CityDataSourceToDataMapper

class GeocodingLiveDataSource(
    private val geocodingDataSourceToDataMapper: CityDataSourceToDataMapper,
    private val geocodingService: GeocodingService,
) : GeocodingDataSource {

    override suspend fun fetchCities(city: String): List<CityDataModel> {
        return geocodingService.fetchCities(city).map {
            geocodingDataSourceToDataMapper.toData(it)
        }
    }

}
