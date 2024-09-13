package pl.org.seva.victorweather.presentation.model

data class CityPresentationModel(
    val uuid: String,
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
)
