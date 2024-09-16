package pl.org.seva.victorweather.domain.model

data class WeatherDomainModel(
    val uuid: String?,
    val temp: Double,
    val clouds: Int,
    val rain: Double?,
    val snow: Double?,
    val humidity: Double,
)
