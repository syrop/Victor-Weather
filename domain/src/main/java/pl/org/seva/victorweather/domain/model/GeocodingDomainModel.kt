package pl.org.seva.victorweather.domain.model

data class GeocodingDomainModel(val cities: List<City>) {

    data class City(
        val name: String,
        val lat: Double,
        val lon: Double,
        val country: String,
    )

}
