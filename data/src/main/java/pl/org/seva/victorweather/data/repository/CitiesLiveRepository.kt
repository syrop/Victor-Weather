package pl.org.seva.victorweather.data.repository

import pl.org.seva.victorweather.data.datasource.CitiesDataSource
import pl.org.seva.victorweather.data.mapper.CityDataToDomainMapper
import pl.org.seva.victorweather.data.mapper.CityDomainToDataMapper
import pl.org.seva.victorweather.data.model.CityDataModel
import pl.org.seva.victorweather.domain.model.CityDomainModel
import pl.org.seva.victorweather.domain.repository.CitiesRepository

class CitiesLiveRepository(
    private val cityDataToDomainMapper: CityDataToDomainMapper,
    private val cityDomainToDataMapper: CityDomainToDataMapper,
    private val citiesDataSource: CitiesDataSource,
) : CitiesRepository {

    private var cityMap: MutableMap<String, CityDataModel> = mutableMapOf()

    override suspend fun findCities(city: String): List<CityDomainModel> {
        cityMap.clear()
        val cities = citiesDataSource.fetchCities(city)
        cities.forEach { cityMap[it.uuid] = it }
        return cities.map { cityDataToDomainMapper.toDomain(it) }
    }

    override fun get(uuid: String): CityDomainModel {
        return cityDataToDomainMapper.toDomain(requireNotNull(cityMap[uuid]) { "Wrong uuid" } )
    }

    override suspend fun save(city: CityDomainModel) {
        citiesDataSource.save(cityDomainToDataMapper.toData(city))
    }

    override suspend fun load(): List<CityDomainModel> {
        return citiesDataSource.load().map { cityDataToDomainMapper.toDomain(it) }
    }

    override suspend fun load(city: String): CityDomainModel {
        return cityDataToDomainMapper.toDomain(citiesDataSource.load(city))
    }

}
