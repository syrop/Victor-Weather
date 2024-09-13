package pl.org.seva.victorweather.data.repository

import pl.org.seva.victorweather.data.datasource.GeocodingDataSource
import pl.org.seva.victorweather.data.mapper.CityDataToDomainMapper
import pl.org.seva.victorweather.data.model.CityDataModel
import pl.org.seva.victorweather.domain.model.CityDomainModel
import pl.org.seva.victorweather.domain.repository.CitiesRepository

class CitiesLiveRepository(
    private val geocodingDataToDomainMapper: CityDataToDomainMapper,
    private val geocodingDataSource: GeocodingDataSource,
) : CitiesRepository {

    private var cityMap: MutableMap<String, CityDataModel> = mutableMapOf()

    override suspend fun findCities(city: String): List<CityDomainModel> {
        cityMap.clear()
        println("wiktor look for cities")
        val cities = try {
            geocodingDataSource.fetchCities(city)
        }
        catch (e: Exception) {
            println("wiktor $e")
            emptyList()
        }
        println("wiktor there are cities")
        cities.forEach { cityMap[it.uuid] = it }
        return cities.map { geocodingDataToDomainMapper.toData(it) }
    }

    override fun get(uuid: String): CityDomainModel {
        return geocodingDataToDomainMapper.toData(requireNotNull(cityMap[uuid]) { "Wrong uuid" } )
    }

}
