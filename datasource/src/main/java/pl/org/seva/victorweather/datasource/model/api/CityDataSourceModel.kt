package pl.org.seva.victorweather.datasource.model.api

data class CityDataSourceModel(
    val uuid: String?,
    val name: String,
    val state: String,
    val lat: Double,
    val lon: Double,
    val country: String,
)
