package pl.org.seva.victorweather.datasource.model

data class GeocodingDataSourceModel(val cities: List<City>) {

    data class City(
        val name: String,
        val lat: Double,
        val lon: Double,
        val country: String,
    )

}
