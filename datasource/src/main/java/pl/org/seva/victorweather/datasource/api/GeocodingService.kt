package pl.org.seva.victorweather.datasource.api

import android.provider.ContactsContract.CommonDataKinds.Im
import pl.org.seva.victorweather.datasource.model.GeocodingDataSourceModel
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingService {

    @GET("direct")
    suspend fun getCities(
        @Query("q") city: String,
        @Query("limit") limit: Int = 5,
        @Query("appid") id: String = APPID,
    ): List<GeocodingDataSourceModel.City>

    companion object {
        const val APPID = "83f8aa22f836aeee8b81c98f63bd1c06"
    }

}
