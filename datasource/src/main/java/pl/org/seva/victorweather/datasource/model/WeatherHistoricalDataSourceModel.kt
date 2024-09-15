package pl.org.seva.victorweather.datasource.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherHistoricalDataSourceModel(
    @PrimaryKey
    val uuid: String,
    val temp: Double,
    val clouds: Int,
    val rain: Double?,
    val snow: Double?,
)
