package pl.org.seva.victorweather.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.org.seva.victorweather.domain.cleanarchitecture.usecase.BackgroundExecutingUseCase
import pl.org.seva.victorweather.domain.model.WeatherDomainModel
import pl.org.seva.victorweather.domain.repository.WeatherRepository

class SaveWeatherUseCase(
    private val weatherRepository: WeatherRepository,
): BackgroundExecutingUseCase<WeatherDomainModel, Unit>() {

    override suspend fun executeInBackground(request: WeatherDomainModel) {
        withContext(Dispatchers.IO) {
            weatherRepository.save(request)
        }
    }

}
