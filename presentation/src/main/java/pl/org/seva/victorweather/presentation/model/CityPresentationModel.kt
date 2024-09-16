package pl.org.seva.victorweather.presentation.model

data class CityPresentationModel(
    val uuid: String,
    val name: String,
    val state: String?,
    val lat: Double,
    val lon: Double,
    val country: String,
) {
    override fun toString() =
        name + if (state != null) " ($state)" else ""
}
