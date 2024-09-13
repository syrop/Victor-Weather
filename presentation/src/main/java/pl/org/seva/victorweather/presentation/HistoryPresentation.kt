package pl.org.seva.victorweather.presentation

import kotlinx.coroutines.CoroutineScope
import pl.org.seva.victorweather.domain.model.CityDomainModel
import pl.org.seva.victorweather.domain.usecase.LoadCitiesUseCase
import pl.org.seva.victorweather.presentation.architecture.BasePresentation
import pl.org.seva.victorweather.presentation.architecture.UseCaseExecutorProvider
import pl.org.seva.victorweather.presentation.mapper.CityDomainToPresentationMapper
import pl.org.seva.victorweather.presentation.viewstate.CityViewState

class HistoryPresentation(
    private val cityDomainToPresentationMapper: CityDomainToPresentationMapper,
    private val loadCitiesUseCase: LoadCitiesUseCase,
    useCaseExecutorProvider: UseCaseExecutorProvider,
) : BasePresentation<CityViewState>(useCaseExecutorProvider) {

    override val initialViewState: CityViewState
        get() = CityViewState()

    fun load(scope: CoroutineScope) {
        updateViewState { loading() }
        loadCitiesUseCase(scope, Unit, ::onCitiesLoaded)
    }

    fun onCitiesLoaded(cities: List<CityDomainModel>) {
        updateViewState {
            copy(
                isLoading = false,
                cities = cities.map { cityDomainToPresentationMapper.toPresentation(it) }
            )
        }
    }

}
