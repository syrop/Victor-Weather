package pl.org.seva.victorweather.data.model

data class GeocodingDataModel(val cities: List<City>) {

    data class City(
        val name: String,
        val lat: Double,
        val lon: Double,
        val country: String,
    )

}
