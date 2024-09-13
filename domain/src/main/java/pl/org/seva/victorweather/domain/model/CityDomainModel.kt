package pl.org.seva.victorweather.domain.model

data class CityDomainModel(
    val uuid: String,
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
)
