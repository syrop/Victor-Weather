package pl.org.seva.victorweather.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.org.seva.victorweather.domain.cleanarchitecture.usecase.BackgroundExecutingUseCase
import pl.org.seva.victorweather.domain.model.CityDomainModel
import pl.org.seva.victorweather.domain.repository.CitiesRepository

class FetchHistoricalCitiesUseCase(
    private val citiesRepository: CitiesRepository,
) : BackgroundExecutingUseCase<Unit, List<CityDomainModel>>() {

    override suspend fun executeInBackground(request: Unit): List<CityDomainModel> {
        return withContext(Dispatchers.IO) {
            citiesRepository.load()
        }
    }

}
