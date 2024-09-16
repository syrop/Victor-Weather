package pl.org.seva.victorweather.presentation

import kotlinx.coroutines.CoroutineScope
import pl.org.seva.victorweather.domain.model.CityDomainModel
import pl.org.seva.victorweather.domain.model.WeatherDomainModel
import pl.org.seva.victorweather.domain.usecase.FetchHistoricalCityUseCase
import pl.org.seva.victorweather.domain.usecase.FetchHistoricalWeatherUseCase
import pl.org.seva.victorweather.presentation.architecture.BasePresentation
import pl.org.seva.victorweather.presentation.architecture.UseCaseExecutorProvider
import pl.org.seva.victorweather.presentation.mapper.CityDomainToPresentationMapper
import pl.org.seva.victorweather.presentation.mapper.WeatherDomainToPresentationMapper
import pl.org.seva.victorweather.presentation.viewstate.CityDetailsViewState

class WeatherHistoryPresentation(
    private val weatherDomainToPresentationMapper: WeatherDomainToPresentationMapper,
    private val cityDomainToPresentationMapper: CityDomainToPresentationMapper,
    private val fetchHistoricalCityUseCase: FetchHistoricalCityUseCase,
    private val fetchHistoricalWeatherUseCase: FetchHistoricalWeatherUseCase,
    useCaseExecutorProvider: UseCaseExecutorProvider,
) : BasePresentation<CityDetailsViewState>(useCaseExecutorProvider) {

    override val initialViewState: CityDetailsViewState
        get() = CityDetailsViewState()

    fun fetchCity(scope: CoroutineScope, uuid: String) {
        updateViewState { loading() }
        fetchHistoricalCityUseCase(scope, uuid, onSuccess = ::onFetchedCity)
    }

    fun onFetchedCity(city: CityDomainModel) {
        updateViewState { withCity(cityDomainToPresentationMapper.toPresentation(city).toString()) }
    }

    fun fetchHistoricalWeather(scope: CoroutineScope, city: String) {
        updateViewState { loading() }
        fetchHistoricalWeatherUseCase(scope, city, ::onWeatherFetched)
    }

    fun onWeatherFetched(weather: WeatherDomainModel) {
        updateViewState { withWeather(weatherDomainToPresentationMapper.toPresentation(weather)) }
    }

}
