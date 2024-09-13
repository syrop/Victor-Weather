package pl.org.seva.victorweather.presentation.mapper

import pl.org.seva.victorweather.domain.model.CityDomainModel
import pl.org.seva.victorweather.presentation.model.CityPresentationModel

class CityDomainToPresentationMapper {

    fun toPresentation(input: CityDomainModel): CityPresentationModel {
        return CityPresentationModel(
            input.uuid,
            input.name,
            input.state,
            input.lat,
            input.lon,
            input.country,
        )
    }

}
