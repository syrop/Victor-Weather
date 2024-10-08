package pl.org.seva.victorweather.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.org.seva.victorweather.domain.cleanarchitecture.usecase.BackgroundExecutingUseCase
import pl.org.seva.victorweather.domain.model.CityDomainModel
import pl.org.seva.victorweather.domain.repository.CitiesRepository

class FindCitiesUseCase(
    private val citiesRepository: CitiesRepository,
) : BackgroundExecutingUseCase<String, List<CityDomainModel>>() {

    override suspend fun executeInBackground(request: String): List<CityDomainModel> {
        return withContext(Dispatchers.IO) {
            citiesRepository.findCities(request)
        }
    }

}
