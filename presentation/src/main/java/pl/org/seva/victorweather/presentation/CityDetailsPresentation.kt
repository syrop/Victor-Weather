package pl.org.seva.victorweather.presentation

import kotlinx.coroutines.CoroutineScope
import pl.org.seva.victorweather.domain.model.CityDomainModel
import pl.org.seva.victorweather.domain.model.WeatherDomainModel
import pl.org.seva.victorweather.domain.usecase.FetchWeatherUseCase
import pl.org.seva.victorweather.domain.usecase.GetCityUseCase
import pl.org.seva.victorweather.domain.usecase.SaveWeatherUseCase
import pl.org.seva.victorweather.presentation.architecture.BasePresentation
import pl.org.seva.victorweather.presentation.architecture.UseCaseExecutorProvider
import pl.org.seva.victorweather.presentation.mapper.CityDomainToPresentationMapper
import pl.org.seva.victorweather.presentation.mapper.WeatherDomainToPresentationMapper
import pl.org.seva.victorweather.presentation.mapper.WeatherPresentationToDomainMapper
import pl.org.seva.victorweather.presentation.model.WeatherPresentationModel
import pl.org.seva.victorweather.presentation.viewstate.CityDetailsViewState

class CityDetailsPresentation(
    private val cityDomainToPresentationMapper: CityDomainToPresentationMapper,
    private val weatherDomainToPresentationMapper: WeatherDomainToPresentationMapper,
    private val weatherPresentationToDomainMapper: WeatherPresentationToDomainMapper,
    private val getCityUseCase: GetCityUseCase,
    private val fetchWeatherUseCase: FetchWeatherUseCase,
    private val saveWeatherUseCase: SaveWeatherUseCase,
    useCaseExecutorProvider: UseCaseExecutorProvider,
) : BasePresentation<CityDetailsViewState>(useCaseExecutorProvider) {

    override val initialViewState: CityDetailsViewState get() = CityDetailsViewState()

    fun getCity(scope: CoroutineScope, uuid: String) {
        updateViewState { loading() }
        getCityUseCase(scope, uuid, onSuccess = { onGotCity(it, scope) })
    }

    fun onGotCity(city: CityDomainModel, scope: CoroutineScope) {
        updateViewState { withCityName(cityDomainToPresentationMapper.toPresentation(city).name) }
        fetchWeatherUseCase(
            scope,
            city.lat to city.lon,
            onSuccess = { weather -> onFetchedWeather(weather, city.uuid, scope) }
        )
    }

    fun onFetchedWeather(weather: WeatherDomainModel, uuid: String, scope: CoroutineScope) {
        val presentationWeather = weatherDomainToPresentationMapper.toPresentation(weather, uuid)
        updateViewState { withWeather(presentationWeather) }
        save(scope, presentationWeather)
    }

    fun save(scope: CoroutineScope, weather: WeatherPresentationModel) {
        saveWeatherUseCase(scope, weatherPresentationToDomainMapper.toDomain(weather))
    }

}
