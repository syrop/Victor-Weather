package pl.org.seva.victorweather.datasource.api

import pl.org.seva.victorweather.datasource.model.CityDataSourceModel
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingService {

    @GET("direct")
    suspend fun fetchCities(
        @Query("q") city: String,
        @Query("limit") limit: Int = 5,
        @Query("appid") id: String = APPID,
    ): List<CityDataSourceModel>

    companion object {
        const val APPID = "83f8aa22f836aeee8b81c98f63bd1c06"
    }

}
