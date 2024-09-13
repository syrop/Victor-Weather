package pl.org.seva.victorweather.data.datasource

import pl.org.seva.victorweather.data.model.CityDataModel

interface GeocodingDataSource {

    suspend fun fetchCities(city: String): List<CityDataModel>

}
