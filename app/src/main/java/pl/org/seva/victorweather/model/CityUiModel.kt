package pl.org.seva.victorweather.model

data class CityUiModel(
    val uuid: String,
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
)
