package pl.org.seva.victorweather.datasource.mapper

import pl.org.seva.victorweather.data.model.WeatherDataModel
import pl.org.seva.victorweather.datasource.model.WeatherHistoricalDataSourceModel

class WeatherDataToDataSourceMapper {

    fun toDataSource(input: WeatherDataModel): WeatherHistoricalDataSourceModel {
        return WeatherHistoricalDataSourceModel(
            requireNotNull(input.uuid) { "Wrong UUID" },
            input.temp,
            input.clouds,
            input.rain,
            input.snow,
            input.humidity,
        )
    }

}
