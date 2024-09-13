package pl.org.seva.victorweather.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.org.seva.victorweather.datasource.model.db.WeatherDataSourceModel
import pl.org.seva.victorweather.datasource.model.db.CityDataSourceModel

@Database(entities = [CityDataSourceModel::class, WeatherDataSourceModel::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

}
