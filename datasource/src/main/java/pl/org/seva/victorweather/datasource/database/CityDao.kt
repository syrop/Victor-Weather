package pl.org.seva.victorweather.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pl.org.seva.victorweather.datasource.model.db.CityDataSourceModel

@Dao
interface CityDao {

    @Query("SELECT * FROM CityDataSourceModel")
    fun getAll(): List<CityDataSourceModel>

    @Insert
    fun insert(city: CityDataSourceModel)

}
