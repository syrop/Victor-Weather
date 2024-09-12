package pl.org.seva.victorweather.data.mapper

import pl.org.seva.victorweather.data.model.GeocodingDataModel
import pl.org.seva.victorweather.domain.model.GeocodingDomainModel

class GeocodingDataToDomainMapper {

    fun toData(input: GeocodingDataModel): GeocodingDomainModel {
        return GeocodingDomainModel(
            input.cities.map {
                GeocodingDomainModel.City(
                    it.name,
                    it.lat,
                    it.lon,
                    it.country,
                )
            }
        )
    }

}
