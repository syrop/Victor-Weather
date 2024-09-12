package pl.org.seva.victorweather.domain.repository

import pl.org.seva.victorweather.domain.model.GeocodingDomainModel

interface CitiesRepository {

    suspend fun findCities(city: String): GeocodingDomainModel

}
