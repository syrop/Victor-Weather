package pl.org.seva.victorweather.data.model

data class WeatherDataModel(
    val temp: Double,
    val clouds: Int,
    val rain: Double?,
    val snow: Double?,
)
