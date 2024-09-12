package pl.org.seva.victorweather.data.datasource

import pl.org.seva.victorweather.data.model.GeocodingDataModel

interface GeocodingDataSource {

    suspend fun getGeocoding(city: String): GeocodingDataModel

}
