package pl.org.seva.victorweather.presentation.model

data class WeatherPresentationModel(
    val uuid: String,
    val temp: Double,
    val clouds: Int,
    val rain: Double?,
    val snow: Double?,
)
