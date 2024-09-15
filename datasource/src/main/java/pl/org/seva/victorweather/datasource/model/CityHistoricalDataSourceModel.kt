package pl.org.seva.victorweather.datasource.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityHistoricalDataSourceModel(
    @PrimaryKey
    val uuid: String,
    val name: String,
    val state: String,
    val country: String,
)
