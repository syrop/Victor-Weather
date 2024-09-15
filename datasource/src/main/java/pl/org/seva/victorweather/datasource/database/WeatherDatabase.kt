package pl.org.seva.victorweather.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.org.seva.victorweather.datasource.model.WeatherHistoricalDataSourceModel
import pl.org.seva.victorweather.datasource.model.CityHistoricalDataSourceModel

@Database(entities = [CityHistoricalDataSourceModel::class, WeatherHistoricalDataSourceModel::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

    abstract fun weatherDao(): WeatherDao

    companion object {
        const val DATABASE_NAME = "weather"
    }

}
