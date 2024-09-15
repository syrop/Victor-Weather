package pl.org.seva.victorweather.datasource.mapper

import pl.org.seva.victorweather.data.model.WeatherDataModel
import pl.org.seva.victorweather.datasource.model.WeatherDataSourceModel
import pl.org.seva.victorweather.datasource.model.WeatherHistoricalDataSourceModel

class WeatherDataSourceToDataMapper {

    fun toData(input: WeatherDataSourceModel): WeatherDataModel {
        return WeatherDataModel(
            null,
            input.current.temp,
            input.current.clouds,
            input.current.rain?.`1h`,
            input.current.snow?.`1h`,
        )
    }

    fun toData(input: WeatherHistoricalDataSourceModel): WeatherDataModel {
        return WeatherDataModel(
            input.uuid,
            input.temp,
            input.clouds,
            input.rain,
            input.snow,
        )
    }

}
