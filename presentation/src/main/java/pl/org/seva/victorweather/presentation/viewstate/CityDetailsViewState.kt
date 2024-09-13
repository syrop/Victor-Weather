package pl.org.seva.victorweather.presentation.viewstate

import pl.org.seva.victorweather.presentation.model.WeatherPresentationModel

data class CityDetailsViewState(
    val isLoading: Boolean = false,
    val cityName: String = "",
    val weather: WeatherPresentationModel? = null,
) {

    fun loading() = copy(
        isLoading = true,
        weather = null,
    )

    fun withCityName(cityName: String) = copy(
        isLoading = false,
        cityName = cityName,
    )

    fun withWeather(weather: WeatherPresentationModel?) = copy(
        isLoading = false,
        weather = weather
    )

}
