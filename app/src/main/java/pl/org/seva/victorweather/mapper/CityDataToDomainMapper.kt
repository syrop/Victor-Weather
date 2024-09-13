package pl.org.seva.victorweather.mapper

import pl.org.seva.victorweather.data.model.CityDataModel
import pl.org.seva.victorweather.domain.model.CityDomainModel
import pl.org.seva.victorweather.model.CityUiModel

class CityDataToUiMapper {

    fun toUi(input: CityDataModel): CityUiModel {
        return CityUiModel(
            input.uuid,
            input.name,
            input.lat,
            input.lon,
            input.country,
        )
    }

}
