package pl.org.seva.victorweather.datasource.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityDataSourceModel(
    @PrimaryKey
    val uuid: String,
    val name: String,
    val state: String,
    val country: String,
)
