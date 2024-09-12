package pl.org.seva.victorweather.presentation.model

data class CityViewState(
    val isLoading: Boolean = false,
    val geocoding: GeocodingPresentationModel = GeocodingPresentationModel(emptyList()),
) {

    fun loading() = copy(isLoading = true)

    fun withGeocoding(geocoding: GeocodingPresentationModel) = copy(
        isLoading = false,
        geocoding = geocoding,
    )

}
