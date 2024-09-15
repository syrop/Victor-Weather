package pl.org.seva.victorweather.presentation.mapper

import pl.org.seva.victorweather.domain.model.WeatherDomainModel
import pl.org.seva.victorweather.presentation.model.WeatherPresentationModel

class WeatherPresentationToDomainMapper {

    fun toDomain(input: WeatherPresentationModel): WeatherDomainModel {
        return WeatherDomainModel(
            input.uuid,
            input.temp,
            input.clouds,
            input.rain,
            input.snow,
        )
    }

}
