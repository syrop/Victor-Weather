package pl.org.seva.victorweather.data.mapper

import pl.org.seva.victorweather.data.model.WeatherDataModel
import pl.org.seva.victorweather.domain.model.WeatherDomainModel

class WeatherDataToDomainMapper {

    fun toDomain(input: WeatherDataModel): WeatherDomainModel {
        return WeatherDomainModel(
            input.temp,
            input.clouds,
            input.rain,
            input.snow,
        )
    }

}
