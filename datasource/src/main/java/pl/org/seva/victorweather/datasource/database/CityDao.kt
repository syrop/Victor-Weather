package pl.org.seva.victorweather.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pl.org.seva.victorweather.datasource.model.CityHistoricalDataSourceModel

@Dao
interface CityDao {

    @Query("SELECT * FROM CityHistoricalDataSourceModel")
    fun getAll(): List<CityHistoricalDataSourceModel>

    @Query("SELECT * FROM CityHistoricalDataSourceModel WHERE uuid = :city")
    fun getCity(city: String): CityHistoricalDataSourceModel

    @Insert
    fun insert(city: CityHistoricalDataSourceModel)

}
