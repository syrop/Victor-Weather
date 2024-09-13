package pl.org.seva.victorweather.presentation

import kotlinx.coroutines.CoroutineScope
import pl.org.seva.victorweather.domain.model.CityDomainModel
import pl.org.seva.victorweather.domain.usecase.GetCityUseCase
import pl.org.seva.victorweather.presentation.architecture.BasePresentation
import pl.org.seva.victorweather.presentation.architecture.UseCaseExecutorProvider
import pl.org.seva.victorweather.presentation.mapper.CityDomainToPresentationMapper
import pl.org.seva.victorweather.presentation.model.CityPresentationModel
import pl.org.seva.victorweather.presentation.viewstate.CityDetailsViewState
import pl.org.seva.victorweather.presentation.viewstate.CityViewState

class CityDetailsPresentation(
    private val cityDomainToPresentationMapper: CityDomainToPresentationMapper,
    private val getCityUseCase: GetCityUseCase,
    useCaseExecutorProvider: UseCaseExecutorProvider,
) : BasePresentation<CityDetailsViewState>(useCaseExecutorProvider) {

    override val initialViewState: CityDetailsViewState get() = CityDetailsViewState()

    fun getCity(scope: CoroutineScope, uuid: String) {
        updateViewState { loading() }
        getCityUseCase(scope, uuid, ::onGotCity)
    }

    fun onGotCity(city: CityDomainModel) {
        updateViewState { withCityName(cityDomainToPresentationMapper.toPresentation(city).name) }
    }

}
