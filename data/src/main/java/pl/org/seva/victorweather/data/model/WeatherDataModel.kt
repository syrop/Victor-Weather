package pl.org.seva.victorweather.data.model

data class WeatherDataModel(
    val uuid: String?,
    val temp: Double,
    val clouds: Int,
    val rain: Double?,
    val snow: Double?,
)
