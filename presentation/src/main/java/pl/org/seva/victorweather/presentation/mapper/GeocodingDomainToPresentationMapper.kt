package pl.org.seva.victorweather.presentation.mapper

import pl.org.seva.victorweather.domain.model.GeocodingDomainModel
import pl.org.seva.victorweather.presentation.model.GeocodingPresentationModel

class GeocodingDomainToPresentationMapper {

    fun toPresentation(input: GeocodingDomainModel): GeocodingPresentationModel {
        return GeocodingPresentationModel(
            input.cities.map {
                GeocodingPresentationModel.City(
                    it.name,
                    it.lat,
                    it.lon,
                    it.country,
                )
            }
        )
    }

}
