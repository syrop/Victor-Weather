package pl.org.seva.victorweather.datasource

import pl.org.seva.victorweather.data.datasource.GeocodingDataSource
import pl.org.seva.victorweather.data.model.GeocodingDataModel
import pl.org.seva.victorweather.datasource.api.GeocodingService
import pl.org.seva.victorweather.datasource.mapper.GeocodingDataSourceToDataMapper
import pl.org.seva.victorweather.datasource.model.GeocodingDataSourceModel

class GeocodingLiveDataSource(
    private val geocodingDataSourceToDataMapper: GeocodingDataSourceToDataMapper,
    private val geocodingService: GeocodingService,
) : GeocodingDataSource {

    override suspend fun getGeocoding(city: String): GeocodingDataModel {
        return geocodingDataSourceToDataMapper.toData(
            GeocodingDataSourceModel(geocodingService.getCities(city).apply {
                println("wiktor size: $size")
            })
        )
    }

}
