package pl.org.seva.victorweather.presentation.viewstate

import pl.org.seva.victorweather.presentation.model.CityPresentationModel

data class CityDetailsViewState(
    val isLoading: Boolean = false,
    val cityName: String = "",
) {

    fun loading() = copy(isLoading = true)

    fun withCityName(cityName: String) = copy(
        isLoading = false,
        cityName = cityName,
    )

}
