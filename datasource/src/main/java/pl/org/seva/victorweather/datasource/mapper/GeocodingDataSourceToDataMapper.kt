package pl.org.seva.victorweather.datasource.mapper

import pl.org.seva.victorweather.data.model.GeocodingDataModel
import pl.org.seva.victorweather.datasource.model.GeocodingDataSourceModel

class GeocodingDataSourceToDataMapper {

    fun toData(input: GeocodingDataSourceModel): GeocodingDataModel {
        return GeocodingDataModel(
            input.cities.map {
                GeocodingDataModel.City(
                    it.name,
                    it.lat,
                    it.lon,
                    it.country,
                )
            }
        )
    }

}
