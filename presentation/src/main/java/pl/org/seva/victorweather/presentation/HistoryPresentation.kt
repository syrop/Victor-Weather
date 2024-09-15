package pl.org.seva.victorweather.presentation

import kotlinx.coroutines.CoroutineScope
import pl.org.seva.victorweather.domain.model.CityDomainModel
import pl.org.seva.victorweather.domain.usecase.FetchHistoricalCitiesUseCase
import pl.org.seva.victorweather.presentation.architecture.BasePresentation
import pl.org.seva.victorweather.presentation.architecture.UseCaseExecutorProvider
import pl.org.seva.victorweather.presentation.mapper.CityDomainToPresentationMapper
import pl.org.seva.victorweather.presentation.viewstate.CityViewState

class HistoryPresentation(
    private val cityDomainToPresentationMapper: CityDomainToPresentationMapper,
    private val fetchHistoricalCitiesUseCase: FetchHistoricalCitiesUseCase,
    useCaseExecutorProvider: UseCaseExecutorProvider,
) : BasePresentation<CityViewState>(useCaseExecutorProvider) {

    override val initialViewState: CityViewState
        get() = CityViewState()

    fun load(scope: CoroutineScope) {
        updateViewState { loading() }
        fetchHistoricalCitiesUseCase(scope, Unit, ::onCitiesLoaded)
    }

    fun onCitiesLoaded(cities: List<CityDomainModel>) {
        updateViewState {
            withCities(cities.map { cityDomainToPresentationMapper.toPresentation(it) })
        }
    }

}
