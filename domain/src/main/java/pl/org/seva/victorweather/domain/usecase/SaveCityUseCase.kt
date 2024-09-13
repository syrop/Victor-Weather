package pl.org.seva.victorweather.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.org.seva.victorweather.domain.cleanarchitecture.usecase.BackgroundExecutingUseCase
import pl.org.seva.victorweather.domain.model.CityDomainModel
import pl.org.seva.victorweather.domain.repository.CitiesRepository

class SaveCityUseCase(
    private val citiesRepository: CitiesRepository,
): BackgroundExecutingUseCase<CityDomainModel, Unit>() {

    override suspend fun executeInBackground(request: CityDomainModel) {
        withContext(Dispatchers.IO) {
            citiesRepository.save(request)
        }
    }

}
