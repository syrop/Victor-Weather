package pl.org.seva.victorweather.data.model

data class CityDataModel(
    val uuid: String,
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
)
