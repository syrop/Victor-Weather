package pl.org.seva.victorweather.presentation.viewstate

import pl.org.seva.victorweather.presentation.model.CityPresentationModel

data class CityViewState(
    val isLoading: Boolean = false,
    val cities: List<CityPresentationModel> = emptyList(),
) {

    fun loading() = copy(isLoading = true, cities = emptyList())

    fun withCities(cities: List<CityPresentationModel>) = copy(
        isLoading = false,
        cities = cities,
    )

}
