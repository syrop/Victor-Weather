package pl.org.seva.victorweather.data.mapper

import pl.org.seva.victorweather.data.model.WeatherDataModel
import pl.org.seva.victorweather.domain.model.WeatherDomainModel

class WeatherDomainToDataMapper {

    fun toData(input: WeatherDomainModel): WeatherDataModel {
        return WeatherDataModel(
            input.uuid,
            input.temp,
            input.clouds,
            input.rain,
            input.snow,
            input.humidity,
        )
    }
}