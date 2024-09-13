package pl.org.seva.victorweather.datasource.model

data class CityDataSourceModel(
    val uuid: String?,
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
)
