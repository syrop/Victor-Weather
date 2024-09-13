package pl.org.seva.victorweather.data.mapper

import pl.org.seva.victorweather.data.model.CityDataModel
import pl.org.seva.victorweather.domain.model.CityDomainModel

class CityDomainToDataMapper {

    fun toData(input: CityDomainModel): CityDataModel {
        return CityDataModel(
            input.uuid,
            input.name,
            input.state,
            input.lat,
            input.lon,
            input.country,
        )
    }

}
