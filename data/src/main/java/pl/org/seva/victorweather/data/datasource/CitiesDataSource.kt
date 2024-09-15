package pl.org.seva.victorweather.data.datasource

import pl.org.seva.victorweather.data.model.CityDataModel

interface CitiesDataSource {

    suspend fun fetchCities(city: String): List<CityDataModel>

    suspend fun save(city: CityDataModel)

    suspend fun load(): List<CityDataModel>

    suspend fun load(city: String): CityDataModel

}
