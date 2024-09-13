package pl.org.seva.victorweather.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.org.seva.victorweather.domain.cleanarchitecture.usecase.BackgroundExecutingUseCase
import pl.org.seva.victorweather.domain.model.WeatherDomainModel
import pl.org.seva.victorweather.domain.repository.WeatherRepository

class FetchWeatherUseCase(
    private val weatherRepository: WeatherRepository,
) : BackgroundExecutingUseCase<Pair<Double, Double>, WeatherDomainModel>() {

    override suspend fun executeInBackground(request: Pair<Double, Double>): WeatherDomainModel {
        val (lat, lon) = request
        return withContext(Dispatchers.IO) {
            weatherRepository.fetchWeather(lat, lon)
        }
    }

}
