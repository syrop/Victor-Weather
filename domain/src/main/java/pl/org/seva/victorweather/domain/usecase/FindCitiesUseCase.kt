package pl.org.seva.victorweather.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.org.seva.victorweather.domain.cleanarchitecture.usecase.BackgroundExecutingUseCase
import pl.org.seva.victorweather.domain.model.GeocodingDomainModel
import pl.org.seva.victorweather.domain.repository.CitiesRepository

class FindCitiesUseCase(
    private val citiesRepository: CitiesRepository,
) : BackgroundExecutingUseCase<String, GeocodingDomainModel>() {

    override suspend fun executeInBackground(request: String): GeocodingDomainModel {
        return withContext(Dispatchers.IO) {
            citiesRepository.findCities(request)
        }
    }

}
