package pl.org.seva.victorweather.domain.repository

import pl.org.seva.victorweather.domain.model.CityDomainModel

interface CitiesRepository {

    suspend fun findCities(city: String): List<CityDomainModel>

    operator fun get(uuid: String): CityDomainModel

    suspend fun save(city: CityDomainModel)

    suspend fun load(): List<CityDomainModel>

    suspend fun load(city: String): CityDomainModel

}
