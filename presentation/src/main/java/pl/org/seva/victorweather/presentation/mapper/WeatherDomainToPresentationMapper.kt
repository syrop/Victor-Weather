package pl.org.seva.victorweather.presentation.mapper

import pl.org.seva.victorweather.domain.model.WeatherDomainModel
import pl.org.seva.victorweather.presentation.model.WeatherPresentationModel

class WeatherDomainToPresentationMapper {

    fun toPresentation(input: WeatherDomainModel) = WeatherPresentationModel(
        input.temp,
        input.clouds,
        input.rain,
        input.snow,
    )

}
