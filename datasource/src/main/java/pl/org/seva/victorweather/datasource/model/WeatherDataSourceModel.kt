package pl.org.seva.victorweather.datasource.model

data class WeatherDataSourceModel(val current: Current) {

    data class Current(
        val temp: Double,
        val clouds: Int,
        val rain: Fall?,
        val snow: Fall?,
        val humidity: Double,
    )

    data class Fall(
        val `1h`: Double,
    )

}
