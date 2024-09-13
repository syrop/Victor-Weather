package pl.org.seva.victorweather.datasource.mapper

import pl.org.seva.victorweather.data.model.WeatherDataModel
import pl.org.seva.victorweather.datasource.model.api.WeatherDataSourceModel

class WeatherDataSourceToDataMapper {

    fun toData(input: WeatherDataSourceModel): WeatherDataModel {
        return WeatherDataModel(
            input.current.temp,
            input.current.clouds,
            input.current.rain?.`1h`,
            input.current.snow?.`1h`,
        )
    }

}
