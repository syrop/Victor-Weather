package pl.org.seva.victorweather.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pl.org.seva.victorweather.datasource.model.WeatherHistoricalDataSourceModel

@Dao
interface WeatherDao {

    @Query("SELECT * FROM WeatherHistoricalDataSourceModel WHERE uuid = :city")
    fun get(city: String): WeatherHistoricalDataSourceModel

    @Insert
    fun insert(weather: WeatherHistoricalDataSourceModel)

}
