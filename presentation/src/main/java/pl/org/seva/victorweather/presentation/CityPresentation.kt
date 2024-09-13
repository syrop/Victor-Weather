package pl.org.seva.victorweather.presentation

import kotlinx.coroutines.CoroutineScope
import pl.org.seva.victorweather.domain.model.CityDomainModel
import pl.org.seva.victorweather.domain.usecase.FindCitiesUseCase
import pl.org.seva.victorweather.domain.usecase.SaveCityUseCase
import pl.org.seva.victorweather.presentation.architecture.BasePresentation
import pl.org.seva.victorweather.presentation.architecture.UseCaseExecutorProvider
import pl.org.seva.victorweather.presentation.mapper.CityDomainToPresentationMapper
import pl.org.seva.victorweather.presentation.mapper.CityPresentationToDomainMapper
import pl.org.seva.victorweather.presentation.model.CityPresentationModel
import pl.org.seva.victorweather.presentation.viewstate.CityViewState

class CityPresentation(
    private val cityDomainToPresentationMapper: CityDomainToPresentationMapper,
    private val cityPresentationToDomainMapper: CityPresentationToDomainMapper,
    private val findCitiesUseCase: FindCitiesUseCase,
    private val saveCityUseCase: SaveCityUseCase,
    useCaseExecutorProvider: UseCaseExecutorProvider,
) : BasePresentation<CityViewState>(useCaseExecutorProvider) {

    override val initialViewState: CityViewState get() = CityViewState()

    fun findCities(scope: CoroutineScope, city: String) {
        updateViewState { loading() }
        findCitiesUseCase(scope, city, ::onCitiesLoaded)
    }

    fun onCitiesLoaded(geocodingDomainModel: List<CityDomainModel>) {
        updateViewState {
            withCities(
                geocodingDomainModel.map {
                    cityDomainToPresentationMapper.toPresentation(it)
                }
            )
        }
    }

    fun save(scope: CoroutineScope, city: CityPresentationModel) {
        saveCityUseCase(scope, cityPresentationToDomainMapper.toDomain(city))
    }

}
