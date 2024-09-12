package pl.org.seva.victorweather.datasource.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class GeocodingServiceFactory {

    fun getGeocodingService(): GeocodingService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GeocodingService::class.java)

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/geo/1.0/"
    }

}
