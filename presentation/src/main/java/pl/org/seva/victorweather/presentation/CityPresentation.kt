package pl.org.seva.victorweather.presentation

import kotlinx.coroutines.CoroutineScope
import pl.org.seva.victorweather.domain.model.GeocodingDomainModel
import pl.org.seva.victorweather.domain.usecase.FindCitiesUseCase
import pl.org.seva.victorweather.presentation.architecture.BasePresentation
import pl.org.seva.victorweather.presentation.architecture.UseCaseExecutorProvider
import pl.org.seva.victorweather.presentation.mapper.GeocodingDomainToPresentationMapper
import pl.org.seva.victorweather.presentation.model.CityViewState
import pl.org.seva.victorweather.presentation.model.GeocodingPresentationModel

class CityPresentation(
    private val geocodingDomainToPresentationMapper: GeocodingDomainToPresentationMapper,
    private val findCitiesUseCase: FindCitiesUseCase,
    useCaseExecutorProvider: UseCaseExecutorProvider,
) : BasePresentation<CityViewState>(useCaseExecutorProvider) {

    override val initialViewState: CityViewState get() = CityViewState(GeocodingPresentationModel(
        emptyList()
    ))

    fun findCities(scope: CoroutineScope, city: String) {
        findCitiesUseCase(scope, city, ::onLoaded)
    }

    fun onLoaded(geocodingDomainModel: GeocodingDomainModel) {
        updateViewState {
            CityViewState(geocodingDomainToPresentationMapper.toPresentation(geocodingDomainModel))
        }
    }


}
