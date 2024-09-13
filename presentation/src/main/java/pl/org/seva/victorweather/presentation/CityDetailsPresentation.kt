package pl.org.seva.victorweather.presentation

import kotlinx.coroutines.CoroutineScope
import pl.org.seva.victorweather.domain.model.CityDomainModel
import pl.org.seva.victorweather.domain.model.WeatherDomainModel
import pl.org.seva.victorweather.domain.usecase.FetchWeatherUseCase
import pl.org.seva.victorweather.domain.usecase.GetCityUseCase
import pl.org.seva.victorweather.presentation.architecture.BasePresentation
import pl.org.seva.victorweather.presentation.architecture.UseCaseExecutorProvider
import pl.org.seva.victorweather.presentation.mapper.CityDomainToPresentationMapper
import pl.org.seva.victorweather.presentation.mapper.WeatherDomainToPresentationMapper
import pl.org.seva.victorweather.presentation.viewstate.CityDetailsViewState

class CityDetailsPresentation(
    private val cityDomainToPresentationMapper: CityDomainToPresentationMapper,
    private val weatherDomainToPresentationMapper: WeatherDomainToPresentationMapper,
    private val getCityUseCase: GetCityUseCase,
    private val fetchWeatherUseCase: FetchWeatherUseCase,
    useCaseExecutorProvider: UseCaseExecutorProvider,
) : BasePresentation<CityDetailsViewState>(useCaseExecutorProvider) {

    override val initialViewState: CityDetailsViewState get() = CityDetailsViewState()

    fun loadCity(scope: CoroutineScope, uuid: String) {
        updateViewState { loading() }
        getCityUseCase(scope, scope to uuid, ::onGotCity)
    }

    fun onGotCity(result: Pair<CoroutineScope, CityDomainModel>) {
        val (scope, city) = result
        updateViewState { withCityName(cityDomainToPresentationMapper.toPresentation(city).name) }
        fetchWeatherUseCase(scope, city.lat to city.lon, ::onFetchedWeather)
    }

    fun onFetchedWeather(weather: WeatherDomainModel) {
        updateViewState { withWeather(weatherDomainToPresentationMapper.toPresentation(weather)) }
    }

}
