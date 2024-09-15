package pl.org.seva.victorweather.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.org.seva.victorweather.domain.cleanarchitecture.usecase.BackgroundExecutingUseCase
import pl.org.seva.victorweather.domain.model.WeatherDomainModel
import pl.org.seva.victorweather.domain.repository.WeatherRepository

class FetchHistoricalWeatherUseCase(
    private val weatherRepository: WeatherRepository,
) : BackgroundExecutingUseCase<String, WeatherDomainModel>() {

    override suspend fun executeInBackground(request: String): WeatherDomainModel {
        return withContext(Dispatchers.IO) {
            weatherRepository.fetchWeatherHistory(request)
        }
    }

}
